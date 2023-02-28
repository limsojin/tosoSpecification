package com.example.demo.todospecification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.model.Todo;
import com.example.demo.model.TodoSpecs.SearchItem;

public class TodoSpecification
{
    public static Specification<Todo> searchWith ( Map<SearchItem, Object> searchItem )
    {
        return ( Specification<Todo> ) ( ( root,
                                           query,
                                           builder ) -> {
            final List<Predicate> predicate = getPredicateWithItem ( searchItem, root, builder );
            return builder.and ( predicate.toArray ( new Predicate [0] ) );
        } );
    }

    private static List<Predicate> getPredicateWithItem ( Map<SearchItem, Object> searchItem,
                                                          Root<Todo> root,
                                                          CriteriaBuilder builder )
    {
        final List<Predicate> predicate = new ArrayList<> ();
        for ( final SearchItem key : searchItem.keySet () )
        {
            switch ( key )
            {
                case KEY :
                case TODO :
                case PLANPICK :
                    predicate.add ( builder.equal ( root.get ( key.getValue () ), searchItem.get ( key ) ) );
                    break;

            } // 입력받은 값과 일치하는 결과를 반환
        }
        return predicate;
    }
}
