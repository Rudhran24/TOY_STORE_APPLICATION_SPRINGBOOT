package com.example.springboot.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Toy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String toyName;

    @OneToMany(mappedBy = "toy", cascade = CascadeType.ALL)
    private List<User> users;

    // Constructors, getters, and setters
    public Toy() {
    }

    public Toy(String toyName) {
        this.toyName = toyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToyName() {
        return toyName;
    }

    public void setToyName(String toyName) {
        this.toyName = toyName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
