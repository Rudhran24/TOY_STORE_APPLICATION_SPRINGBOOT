package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.springboot.model.Toy;
import com.example.springboot.service.ToyService;

import java.util.List;

@RestController
@RequestMapping("/api/toy")
public class ToyController {

    @Autowired
    private ToyService toyService;

    @PostMapping
    public ResponseEntity<Toy> addToy(@RequestBody Toy toy) {
        Toy newToy = toyService.create(toy);
        return new ResponseEntity<>(newToy, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Toy>> getAllToys() {
        List<Toy> toys = toyService.getAllToys();
        return new ResponseEntity<>(toys, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Toy> getToyById(@PathVariable("id") int id) {
        Toy toy = toyService.getToyById(id).orElse(null);
        if (toy != null) {
            return new ResponseEntity<>(toy, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Toy> updateToy(@PathVariable("id") int id, @RequestBody Toy toy) {
        if (toyService.updateToy(id, toy)) {
            return new ResponseEntity<>(toy, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteToy(@PathVariable("id") int id) {
        if (toyService.deleteToy(id)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Toy>> getAllToysSorted(@RequestParam String sortBy) {
        List<Toy> toys = toyService.getAllToysSortedBy(sortBy);
        return new ResponseEntity<>(toys, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Toy>> getAllToysPaginated(@RequestParam int pageNo, @RequestParam int pageSize) {
        Page<Toy> toysPage = toyService.getAllToysPaginated(pageNo, pageSize);
        return new ResponseEntity<>(toysPage, HttpStatus.OK);
    }
    
    @GetMapping("/name/{toyName}")
    public ResponseEntity<List<Toy>> getToysByToyName(@PathVariable String toyName) {
        List<Toy> toys = toyService.findByToyName(toyName);
        return new ResponseEntity<>(toys, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Toy>> getToysByUserId(@PathVariable int userId) {
        List<Toy> toys = toyService.findByUserId(userId);
        return new ResponseEntity<>(toys, HttpStatus.OK);
    }
}
