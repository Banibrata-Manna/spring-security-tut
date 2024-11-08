package com.in28minutes.learn_spring_security.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoResource {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final List<Todo> TODOS_LIST = List.of(new Todo("Banibrata", "Learn Spring Security"),
			new Todo("Banibrata", "Make College Placement Cell"));

	@GetMapping("/todos")
	public List<Todo> retrieveAllTodos() {
		return TODOS_LIST;
	}
	@GetMapping("/users/{username}/todos")
	public Todo retirieveTodosForSpecificUser(@PathVariable("username") String username) {
		return TODOS_LIST.get(1);
	}
	@PostMapping("/users/{username}/todos")
	public void createTodoforSpecificUser(@PathVariable("username") String username,
			@RequestBody Todo todo) {
		logger.info("Create {} for {}", todo, username);
	}
}

record Todo(String username, String description) {}