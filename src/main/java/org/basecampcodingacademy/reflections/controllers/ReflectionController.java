package org.basecampcodingacademy.reflections.controllers;

import org.basecampcodingacademy.reflections.db.ReflectionRepository;
import org.basecampcodingacademy.reflections.domain.Reflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reflections")
public class ReflectionController {
    @Autowired
    public ReflectionRepository reflections;

    @GetMapping
    public List<Reflection> index() {
        return reflections.all();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reflection create(@RequestBody Reflection reflection) {
        return reflections.create(reflection);
    }

    @GetMapping("/today")
    public Reflection today() {
        var reflection =  reflections.find(LocalDate.now());
        return reflection;
    }

    @PatchMapping("/{id}")
    public Reflection update(@PathVariable Integer id, @RequestBody Reflection reflection) {
        reflection.id = id;
        return reflections.update(reflection);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        reflections.delete(id);
    }
}
