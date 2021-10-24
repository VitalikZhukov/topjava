package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);
    //Map<ID authUserId, Map<meal.id, mael>>
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Meal save(int userID, Meal meal) {
        log.info("save {} {}", userID, meal);
        //save
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            getUserIdMealRepository(userID).put(meal.getId(), meal);
            return meal;
        }
        //update
        //  Если ключ есть, то вычисляем лямбда выражение и применяем к значению по ключу
        return getUserIdMealRepository(userID).computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int userID, int id) {
        log.info("delete {} {}", userID, id);
        return getUserIdMealRepository(userID).remove(id) != null;
    }

    @Override
    public Meal get(int userID, int id) {
        log.info("get {} {}", userID, id);
        return getUserIdMealRepository(userID).get(id);
    }

    @Override
    public List<Meal> getAll(int userID) {
        log.info("getAll");
        return getUserIdMealRepository(userID).values().stream()
                .sorted(Comparator.comparing(Meal::getDate).reversed())
                .collect(Collectors.toList());
    }

//    Проверяем наличие такого ключа в map. Если ключ есть и значение по ключу не равно null, то ничего не делаем
//    Иначе (нет ключа или значение по ключу равно null) считаем значение, применяя mappingFunction к key
//    Если итоговое значение не равно null, то записываем пару ключ-значение в map

    private Map<Integer, Meal> getUserIdMealRepository(int id) {
        return repository.computeIfAbsent(id, mappingFunction -> new ConcurrentHashMap<>());
    }
}

