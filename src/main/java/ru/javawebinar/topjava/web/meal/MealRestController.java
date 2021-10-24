package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }


    public List<Meal> getAll(int userID) {
        log.info("getAll");
        return service.getAll(userID);
    }

    public Meal get(int userID, int id) {
        log.info("get {}", id);
        return service.get(userID, id);
    }

    public Meal create(int userID, Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(userID, meal);
    }

    public void delete(int userID, int id) {
        log.info("delete {}", id);
        service.delete(userID, id);
    }

    public void update(int userID, Meal meal, int id) {
        log.info("update {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        service.update(userID, meal);
    }

}