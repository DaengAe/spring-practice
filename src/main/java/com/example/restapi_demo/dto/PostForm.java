package com.example.restapi_demo.dto;

import com.example.restapi_demo.entity.Post;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class PostForm {
    private Long id;
    private String title;
    private String content;

    public Post toEntity() {
        return new Post(id, title, content);
    }
}
