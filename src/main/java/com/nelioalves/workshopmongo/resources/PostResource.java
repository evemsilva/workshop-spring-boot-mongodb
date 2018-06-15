package com.nelioalves.workshopmongo.resources;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.resources.util.URL;
import com.nelioalves.workshopmongo.service.PostService;
import com.nelioalves.workshopmongo.service.exception.ObjectNotFoundException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable("id") String id) throws ObjectNotFoundException {
	    return ResponseEntity.ok(postService.findById(id));
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<Post>> findByText(@RequestParam(value = "text", defaultValue = "") String text) throws ObjectNotFoundException {
        return ResponseEntity.ok(postService.findByTitle(URL.decodeParam(text)));
    }

    @GetMapping(value = "/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(
		    @RequestParam(value="text", defaultValue="") String text,
		    @RequestParam(value="minDate", defaultValue="") String minDate,
		    @RequestParam(value="maxDate", defaultValue="") String maxDate) throws ObjectNotFoundException {

        text = URL.decodeParam(text);
	Date min = URL.convertDate(minDate, new Date(0L));
	Date max = URL.convertDate(maxDate, new Date());

	return ResponseEntity.ok(postService.fullSearch(text, min, max));
    }

}
