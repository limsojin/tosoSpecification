package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Todo;
import com.example.demo.resultvm.EnumResultCode2;
import com.example.demo.resultvm.ResultMsg;
import com.example.demo.service.TodoService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping ( "/todo" )
public class TodoController
{

    @NonNull
    private final TodoService todoService;

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
    @GetMapping
    public List<Todo> findTodos ()
    {
        return this.todoService.findTodo ();
    }

    /**
     * 리턴
     *
     * <pre>
     * contents
     *
     * </pre>
     *
     * @param id
     * @return
     */
    @GetMapping ( "/{key}" )
    public Todo getTodo ( @PathVariable Double key )
    {
        return this.todoService.getTodo ( key );
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
    @PostMapping ( "/save" )
    public Object saveTodo ( @RequestBody @Valid Todo todo,

                             BindingResult bindingResult )
    {
        System.out.println ( todo );
        return this.validation ( todo, bindingResult );
        // return this.todoService.saveTodoList ( todo );
    }

    /**
     * 데이터 검증
     *
     * <pre>
     * contents
     *
     * </pre>
     *
     * @param todo
     * @param bindingResult
     * @return
     */
    private Object validation ( @Valid Todo todo,
                                BindingResult bindingResult )
    {
        if ( bindingResult.hasErrors () )
        {
            return new ResultMsg ( EnumResultCode2.TODO_ERROR );
        }
        /*
         * final Todo newTodo = new Todo ();
         * newTodo.setTodo ( todo.getTodo () );
         * newTodo.setCompleted ( todo.isCompleted () );
         */

        return this.todoService.saveTodoList ( todo );
    }

    /**
     * 수정
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
    @PutMapping ( "/{key}" )
    public Todo updateTodo ( @PathVariable Double key,
                             @RequestBody Todo todo )
    {

        return this.todoService.updateTodoList ( key, todo );
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
    @DeleteMapping ( "/{key}" )
    public void deleteTodo ( @PathVariable Double key )
    {

        this.todoService.deleteTodoList ( key );
    }

    /**
     * 모두 삭제
     *
     * <pre>
     * contents
     *
     * </pre>
     *
     * @param id
     */
    @DeleteMapping ( "/clear" )
    public void deleteAllTodo ()
    {

        this.todoService.deleteAllTodoList ();
    }

    /**
     * 검색을 위한
     *
     * <pre>
     * contents
     *
     * </pre>
     *
     * @param searchRequest
     * @return
     */
    @GetMapping ( "/list" )
    public List<Todo> geTodoList ( @RequestParam ( required = false ) Map<String, Object> searchRequest )
    {
        return this.todoService.geTodoList ( searchRequest );
    }
}
