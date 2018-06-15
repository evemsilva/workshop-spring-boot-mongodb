package com.nelioalves.workshopmongo.service;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.repository.PostRepository;
import com.nelioalves.workshopmongo.service.exception.ObjectNotFoundException;
import java.util.Date;
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

        List<Post> posts = this.repo.searchTitle(title);

        if(posts.isEmpty()) {
            throw new ObjectNotFoundException("Objeto não encontrado");
        }

        return posts;
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate) throws ObjectNotFoundException {

	maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
	List<Post> posts = this.repo.fullSearch(text, minDate, maxDate);

	if(posts.isEmpty()) {
	    throw new ObjectNotFoundException("Objeto não encontrado");
	}

	return posts;
    }

}
