package com.example.springboot.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.springboot.model.Order;
import com.example.springboot.repository.OrderRepo;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepository;

    public Order create(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(int id) {
        return orderRepository.findById(id);
    }

    public boolean updateOrder(int id, Order order) {
        if (!orderRepository.existsById(id)) {
            return false;
        }
        order.setId(id);
        orderRepository.save(order);
        return true;
    }

    public boolean deleteOrder(int id) {
        if (!orderRepository.existsById(id)) {
            return false;
        }
        orderRepository.deleteById(id);
        return true;
    }

    public List<Order> getAllOrdersSortedBy(String sortBy) {
        return orderRepository.findAll(Sort.by(sortBy));
    }

    public Page<Order> getAllOrdersPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return orderRepository.findAll(pageable);
    }
}
