package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Todo;
import com.example.demo.model.TodoSpecs.SearchItem;
import com.example.demo.repository.TodoRepository;
import com.example.demo.todospecification.TodoSpecification;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class TodoServiceImp implements TodoService
{
    @NonNull
    private final TodoRepository todoRepository;

    /**
     * JSON형태로 return
     *
     * <pre>
     * contents
     *
     * </pre>
     *
     * @param id
     * @return
     */
    @Override
    public Todo getTodo ( Double key )
    {
        return this.todoRepository.findById ( key ).orElseThrow ( IllegalArgumentException::new );
    }

    /**
     * 조회
     *
     * <pre>
     * contents
     *
     * </pre>
     *
     * @return
     */
    @Override
    public List<Todo> findTodo ()
    {
        return this.todoRepository.findAll ();
    }

    /**
     * 등록
     *
     * <pre>
     * contents
     *
     * </pre>
     *
     * @param todo
     * @return
     */
    @Override
    public Todo saveTodoList ( Todo todo )
    {
        return this.todoRepository.save ( todo );
    }

    /**
     * 삭제
     *
     * <pre>
     * contents
     *
     * </pre>
     *
     * @param id
     */
    @Override
    public void deleteTodoList ( Double key )
    {
        // this.todoListRepository.deleteById ( id );
        this.todoRepository.deleteById ( key );
    }

    /**
     * 모두삭제
     *
     * <pre>
     * contents
     *
     * </pre>
     *
     * @param todo
     */
    @Override
    public void deleteAllTodoList ()
    {
        this.todoRepository.deleteAll ();
    }

    /**
     * 수정 및 갱신
     *
     * <pre>
     * contents
     *
     * </pre>
     *
     * @param id
     * @param todo
     * @return
     */
    @Override
    public Todo updateTodoList ( Double key,
                                 Todo todo )
    {
        // finById 에서 Id 는 Primary key 를 의미한다.

        final Todo todoList = this.todoRepository.findById ( key ).orElseThrow ( IllegalArgumentException::new );

        todoList.update ( todo.getTodo (), todo.isDone (), todo.getPlanPick () );

        return todoList;

        // todoList.setTodo ( todo );

        /*
         * final Todo todoData = this.todoListRepository.findById ( id ).orElseThrow ( IllegalArgumentException::new
         * );
         * todoData.update ( todo.getKey (), todo.getTodo (), todo.isDone (), todo.getPlanPick () );
         * return todoData;
         */

    }

    @Override
    public List<Todo> geTodoList ( Map<String, Object> searchRequest )
    {
        final Map<SearchItem, Object> searchItem = new HashMap<> ();
        for ( final String key : searchRequest.keySet () )
        {
            searchItem.put ( SearchItem.valueOf ( key.toUpperCase () ), searchRequest.get ( key ) );
        }
        return searchItem.isEmpty () ? this.todoRepository.findAll ()
                        : this.todoRepository.findAll ( TodoSpecification.searchWith ( searchItem ) );
    }
}
