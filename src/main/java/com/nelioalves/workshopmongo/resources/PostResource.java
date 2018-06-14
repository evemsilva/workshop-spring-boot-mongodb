package com.nelioalves.workshopmongo.resources;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.service.PostService;
import com.nelioalves.workshopmongo.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable("id") String id) throws ObjectNotFoundException {
	return ResponseEntity.ok(postService.findById(id));
    }

}
