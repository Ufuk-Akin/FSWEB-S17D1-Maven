package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//localhost:8585

@RestController
@RequestMapping("workintech/animal")
public class AnimalController {

    private Map<Integer , Animal> animals; // Animals map.

    @PostConstruct // Proje ayağa kalkarken bu method devreye giriyor.
    public void init() {
        System.out.println("post construct worked.");
        this.animals = new HashMap<>();
        this.animals.put(1,new Animal(1,"horse")); // Default 1 adet ekledik.
    }

    @GetMapping
    public List<Animal> getAnimals() {
        System.out.println("getAnimals worked.");
        return new ArrayList<>(this.animals.values());
    }


    //localhost:8585/workintech/animal/99
    @GetMapping("/{id}")
    public Animal getAnimal(@PathVariable("id") Integer id) {
        if(id<0) {
            System.out.println("id can not be less than 0. ID: " + id);
            return null;
        }
        return this.animals.get(id);
    }

    @PostMapping
    public void addAnimal(@RequestBody Animal animal) {
        System.out.println("addAnimal worked.");
        this.animals.put(animal.getId(), animal);
    }

    @PutMapping ("@{id}")
    public Animal updateAnimal(@PathVariable("id") Integer id, @RequestBody Animal newAnimal) {
        this.animals.replace(id,newAnimal);
        return this.animals.get(id);
    }

    @DeleteMapping ("{id}")
    public void deleteAnimal(@PathVariable("id") Integer id) {
        this.animals.remove(id);
    }

}
