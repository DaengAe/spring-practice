package com.example.restapi_demo.controller;

import com.example.restapi_demo.dto.PostForm;
import com.example.restapi_demo.entity.Post;
import com.example.restapi_demo.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/api/posts")
    public List<Post> index() {
        return postService.index();
    }

    @GetMapping("/api/posts/{id}")
    public Post show(@PathVariable Long id) {
        return postService.show(id);
    }

    @PostMapping("/api/posts")
    public ResponseEntity<Post> create(@RequestBody PostForm dto) {
        Post created = postService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/api/posts/{id}")
    public ResponseEntity<Post> update(@PathVariable Long id, @RequestBody PostForm dto) {
        Post updated = postService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/api/posts/{id}")
    public ResponseEntity<Post> delete(@PathVariable Long id) {
        Post deleted = postService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
