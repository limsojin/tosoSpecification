package com.example.demo.model;

import org.springframework.data.jpa.domain.Specification;

public class TodoSpecs
{
    /**
     * TodoSpecs에서 지원할 검색 조건을 enum으로 정의
     *
     * <pre>
     * Contents
     *
     * </pre>
     *
     * @author dlath
     * @since 2022. 10. 5.
     * @version
     */
    public enum SearchItem
    {
        KEY("key"), TODO("todo"), DONE("done"), PLANPICK("planPick");

        private final String value;

        SearchItem ( String value )
        {
            this.value = value;

        }

        public String getValue ()
        {
            return this.value;
        }
    }

    public static Specification<Todo> withTodo ( String todo )
    {
        return ( Specification<Todo> ) ( ( root,
                                           query,
                                           builder ) -> builder.equal ( root.get ( "todo" ), todo ) );
    }
}
