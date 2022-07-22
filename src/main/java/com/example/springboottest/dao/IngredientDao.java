package com.example.springboottest.dao;

import com.example.springboottest.data.Ingredient;

public interface IngredientDao {

    Iterable<Ingredient> findAll();

    Ingredient findOne(String id);

    Ingredient save(Ingredient ingredient);

}
