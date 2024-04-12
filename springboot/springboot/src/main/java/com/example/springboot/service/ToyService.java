package com.example.springboot.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.springboot.model.Toy;
import com.example.springboot.repository.ToyRepo;

@Service
public class ToyService {

    @Autowired
    ToyRepo toyRepository;

    public Toy create(Toy toy) {
        return toyRepository.save(toy);
    }

    public List<Toy> getAllToys() {
        return toyRepository.findAll();
    }

    public Optional<Toy> getToyById(int id) {
        return toyRepository.findById(id);
    }

    public boolean updateToy(int id, Toy toy) {
        if (!toyRepository.existsById(id)) {
            return false;
        }
        toy.setId(id);
        toyRepository.save(toy);
        return true;
    }

    public boolean deleteToy(int id) {
        if (!toyRepository.existsById(id)) {
            return false;
        }
        toyRepository.deleteById(id);
        return true;
    }

    public List<Toy> getAllToysSortedBy(String sortBy) {
        return toyRepository.findAll(Sort.by(sortBy));
    }

    public Page<Toy> getAllToysPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return toyRepository.findAll(pageable);
    }

    public List<Toy> findByToyName(String toyName) {
        return toyRepository.findByToyName(toyName);
    }

    public List<Toy> findByUserId(int userId) {
        return toyRepository.findByUserId(userId);
    }

}
