package com.example.restapi_demo.repository;

import com.example.restapi_demo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Override
    ArrayList<Post> findAll();
}
