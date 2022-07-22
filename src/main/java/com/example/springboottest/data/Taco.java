package com.example.springboottest.data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Taco {

    private Long id;

    private Date createdAt;

    @NotNull
//    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<String> ingredients;

    public Taco(Long id, Date createdAt, String name, List<String> ingredients) {
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
        this.ingredients = ingredients;
    }

    public Taco() {
    }
}
