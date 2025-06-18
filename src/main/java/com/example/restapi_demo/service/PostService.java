package com.example.restapi_demo.service;

import com.example.restapi_demo.dto.PostForm;
import com.example.restapi_demo.entity.Post;
import com.example.restapi_demo.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> index() {
        return postRepository.findAll();
    }

    public Post show(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public Post create(PostForm dto) {
        Post post = dto.toEntity();
        if (post.getId() != null) {
            return null;
        }
        return postRepository.save(post);
    }

    public Post update(Long id, PostForm dto) {
        Post post = dto.toEntity();
        log.info("id: {}, article: {}", id, post.toString());

        Post target = postRepository.findById(id).orElse(null);

        if (target == null || !id.equals(post.getId())) {
            log.info("잘못된 요청. id: {}, article: {}", id, post.toString());
            return null;
        }

        target.patch(post);
        Post updated = postRepository.save(target);
        return updated;
    }

    public Post delete(Long id) {
        Post target = postRepository.findById(id).orElse(null);

        if (target == null) {
            return null;
        }

        postRepository.delete(target);
        return target;
    }

}
