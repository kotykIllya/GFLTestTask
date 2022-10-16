package com.example.demo.dao;

import com.example.demo.entity.Expression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpressionDao extends JpaRepository<Expression, Integer>{

//    Optional<Expression> findById(int id);

    @Query("SELECT e FROM Expression e WHERE e.result = :query")
    List<Expression> searchByExactResult(double query);

    @Query("SELECT e FROM Expression e WHERE e.result > :query")
    List<Expression> searchBiggerThan(double query);

    @Query("SELECT e FROM Expression e WHERE e.result < :query")
    List<Expression> searchLessThan(double query);
}
