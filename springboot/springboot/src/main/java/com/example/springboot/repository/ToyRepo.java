package com.example.springboot.repository;

import com.example.springboot.model.Toy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ToyRepo extends JpaRepository<Toy, Integer> {

    @Query("SELECT t FROM Toy t WHERE t.toyName = ?1")
    List<Toy> findByToyName(String toyName);
    
    @Query("SELECT t FROM Toy t WHERE t.id IN (SELECT DISTINCT t.id FROM Toy t INNER JOIN t.users u WHERE u.id = ?1)")
    List<Toy> findByUserId(int userId);
}
