package com.examly.toystore.repository;

import com.examly.toystore.model.Toy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ToyRepository extends JpaRepository<Toy, Long> {

    // List<Toy> findByCategoryId(Long categoryId);

    // @Query("SELECT COUNT(t) FROM Toy t WHERE t.category.id = :categoryId")
    // long countToysByCategoryId(@Param("categoryId") Long categoryId);
    @Query("SELECT t FROM Toy t WHERE t.price > :price")
    List<Toy> findToysByPriceGreaterThan(@Param("price") double price);


}
