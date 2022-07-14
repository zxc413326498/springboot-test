package com.example.springboottest.controller;

import com.example.springboottest.data.Ingredient;
import com.example.springboottest.data.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
//​
/**
 * 5-2.1.2创建控制器类
 */
@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {
    @GetMapping
    public String showDesignForm(Model model) {
        return getIngredients(model);
    }

    @PostMapping
    public String processDesign(@Valid Taco design,Model model, Errors errors) {
        if(errors.hasErrors()){
            return getIngredients(model);
        }
        // Save the taco design...
        // We'll do this in chapter 3
        log.info("Processing design: " + design);
        return "redirect:/orders/current";
    }

    private String getIngredients(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
        );

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            System.out.println("DesignTacoController.showDesignForm" + "----type值= " + type);
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        model.addAttribute("design", new Taco());//new Taco()
        return "design";
    }

    // provided by 'aexiaosong'
    /**
     * 对象过滤 filter(x -> x.getType().equals(type)，过滤（筛选出）list的对象中的属性包含此type的对象
     * @param ingredients 原始数组
     * @param type 对象属性
     * @return 返回过滤后的数组
     */
    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        System.out.println("DesignTacoController.filterByType" + "----ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList())值= " +ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList()) );
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }
}