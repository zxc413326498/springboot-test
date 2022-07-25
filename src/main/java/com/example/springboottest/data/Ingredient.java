package com.example.springboottest.data;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@RequiredArgsConstructor
//3.2jpa private报错"Class '' should have [public, protected] no-arg constructor",
// 只能使用private
@NoArgsConstructor(access= AccessLevel.PUBLIC, force=true)
@Entity//3.2jpa
public class Ingredient {
    @Id
    private final String id;
    private final String name;
    private final Type type;

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
