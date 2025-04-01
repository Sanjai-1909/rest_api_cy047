package com.examly.toystore.service;

import com.examly.toystore.model.Toy;
import com.examly.toystore.repository.ToyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToyService {

    @Autowired
    private ToyRepository toyRepository;

    public List<Toy> getAllToys() {
        return toyRepository.findAll();
    }

    public Optional<Toy> getToyById(Long id) {
        return toyRepository.findById(id);
    }

    public Toy createToy(Toy toy) {
        return toyRepository.save(toy);
    }

    public Toy updateToy(Long id, Toy toy) {
        if (toyRepository.existsById(id)) {
            toy.setId(id);
            return toyRepository.save(toy);
        }
        return null;
    }

    public boolean deleteToy(Long id) {
        if (toyRepository.existsById(id)) {
            toyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // public long countToysByCategoryId(Long categoryId) {
    //     return toyRepository.countToysByCategoryId(categoryId);
    // }

    public Page<Toy> getToysPaginated(int page, int size) {
        Pageable p= PageRequest.of(page,size);
        return toyRepository.findAll(p);
    }
    public List<Toy>getSort(){
        return toyRepository.findAll(Sort.by("price").ascending());
    }
    public List<Toy> getToysByPriceGreaterThan(double price) {
        return toyRepository.findToysByPriceGreaterThan(price);
    }
    
    
}