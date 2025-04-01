package com.examly.toystore.controller;

import com.examly.toystore.model.Toy;
import com.examly.toystore.service.ToyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/toys")
public class ToyController {

    @Autowired
    private ToyService toyService;

    @GetMapping
    public List<Toy> getAllToys() {
        return toyService.getAllToys();
    }

    @GetMapping("/{id}")
    public Optional<Toy> getToyById(@PathVariable Long id) {
        return toyService.getToyById(id);
    }

    @PostMapping("/post")
    public Toy createToy(@RequestBody Toy toy) {
        return toyService.createToy(toy);
    }

    @PutMapping("/{id}")
    public Toy updateToy(@PathVariable Long id, @RequestBody Toy toy) {
        return toyService.updateToy(id, toy);
    }

    @DeleteMapping("/{id}")
    public String deleteToy(@PathVariable Long id) {
        return toyService.deleteToy(id) ? "Toy deleted successfully" : "Toy not found";
    }

    @GetMapping("/page")
    public Page<Toy> getToysPaginated(@RequestParam int page, @RequestParam int size) {
        return toyService.getToysPaginated(page, size);
    }
    @GetMapping("/sort")
    public List<Toy> hi() {
        return toyService.getSort();
    }
    @GetMapping("/price/{price}")
public List<Toy> getToysByPrice(@PathVariable double price) {
    return toyService.getToysByPriceGreaterThan(price);
}


}
