package com.workintech.fswebs17d1.controller;


import com.workintech.fswebs17d1.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//http://localhost:8585

@RestController
@RequestMapping(path = "/workintech/animal")
public class AnimalController {

    private Map<Integer, Animal> animals;


    @Value("${project.developer.fullname}")
    private String projectDeveloperFullname;

    @Value("${course.name}")
    private String courseName;

    @PostConstruct

    public void loadAll(){
        System.out.println("Loading animals...");

        this.animals = new HashMap<>();

        this.animals.put(1, new Animal(1, "Dog"));

    }


    @GetMapping("/config")
    public String getCustomCongifValues() {
        return projectDeveloperFullname + " " + courseName;
    }


    @GetMapping

    public List<Animal> getAnimals() {
        System.out.println("Getting animals...");
        return new ArrayList<>(this.animals.values());
    }

    @GetMapping("{id}")
    public Animal getAnimal(@PathVariable("id")int id) {
        System.out.println("Getting animal with id: " + id);

        if(id<0){
            System.out.println("Invalid animal id: " + id);
            return null;
        }
        return this.animals.get(id);
    }


    @PostMapping
    public void addAnimal(@RequestBody Animal animal) {
        this.animals.put(animal.getId(), animal);
    }

    @PutMapping("{id}")

    public Animal updateAnimal(@PathVariable("id") int id, @RequestBody Animal newAnimal) {
        System.out.println("Updating animal with id: " + id);
        this.animals.replace(id, newAnimal);
        return this.animals.get(id);
    }

    @DeleteMapping("{id}")

    public void deleteAnimal(@PathVariable("id") int id) {

        if(id<0){
            System.out.println("Invalid animal id: " + id);

        }
        this.animals.remove(id);
    }

}
