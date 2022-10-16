package com.example.demo.service;

import com.example.demo.entity.Expression;

import javax.script.ScriptException;
import java.util.List;
import java.util.Optional;

public interface ExpressionService {

    Expression saveToDB(Expression expression);
    double calculation(String expression) throws ScriptException;
    List<Expression> searchByExactResult(double query);
    List<Expression> searchBiggerThan(double query);
    List<Expression> searchLessThan(double query);
}
