package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table ( name = "newtodo" )
public class Todo
{
    /*
     * @GeneratedValue ( strategy = GenerationType.IDENTITY )
     * private Long id;
     */
    @Id
    private Double key;
    private String todo;
    private boolean done;
    private String planPick;

    public void update (
                         String todo,
                         boolean done,
                         String planPick )
    {
        this.todo = todo;
        this.done = done;
        this.planPick = planPick;
    }
}
