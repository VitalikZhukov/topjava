package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealRepository {
    // null if updated meal do not belong to userId
    Meal save(int userID, Meal meal);

    // false if meal do not belong to userId
    boolean delete(int userID, int id);

    // null if meal do not belong to userId
    Meal get(int userID, int id);

    // ORDERED dateTime desc
    Collection<Meal> getAll(int userID);
}
