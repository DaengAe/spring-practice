package com.example.restapi_demo.service;

import com.example.restapi_demo.dto.CommentDto;
import com.example.restapi_demo.entity.Comment;
import com.example.restapi_demo.entity.Post;
import com.example.restapi_demo.repository.CommentRepository;
import com.example.restapi_demo.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    public List<CommentDto> comments(Long postId) {
        return commentRepository.findByPostId(postId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long postId, CommentDto dto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

        Comment comment = Comment.createComment(dto, post);

        Comment created = commentRepository.save(comment);

        return CommentDto.createCommentDto(created);
    }

    public CommentDto update(Long id, CommentDto dto) {
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 없음"));

        target.patch(dto);

        Comment updated = commentRepository.save(target);

        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("대상 없음"));

        commentRepository.delete(target);

        return CommentDto.createCommentDto(target);
    }

}
