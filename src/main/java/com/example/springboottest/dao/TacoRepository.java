package com.example.springboottest.dao;

import com.example.springboottest.data.Taco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacoRepository extends JpaRepository<Taco, Long> {
}