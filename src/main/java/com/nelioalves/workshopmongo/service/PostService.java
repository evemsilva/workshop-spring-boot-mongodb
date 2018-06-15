package com.nelioalves.workshopmongo.service;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.repository.PostRepository;
import com.nelioalves.workshopmongo.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public Post findById(String id) throws ObjectNotFoundException {
        return this.repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findByTitle(String title) throws ObjectNotFoundException {

        List<Post> posts = this.repo.findByTitleContainingIgnoreCase(title);

        if(posts.isEmpty()) {
            throw new ObjectNotFoundException("Objeto não encontrado");
        }

        return posts;
    }

}
