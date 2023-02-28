package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.model.Todo;

@Service
public interface TodoService
{
    Todo getTodo ( Double key );

    List<Todo> findTodo ();

    Todo saveTodoList ( Todo todo );

    void deleteTodoList ( Double key );

    void deleteAllTodoList ();

    Todo updateTodoList ( Double key,
                          Todo todo );

    List<Todo> geTodoList ( Map<String, Object> searchRequest );
}
