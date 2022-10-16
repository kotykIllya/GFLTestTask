package com.example.demo.service.impl;

import com.example.demo.dao.ExpressionDao;
import com.example.demo.entity.Expression;
import com.example.demo.service.ExpressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExpressionServiceImpl implements ExpressionService {

    private final ExpressionDao expressionDao;

    @Autowired
    public ExpressionServiceImpl(ExpressionDao expressionDao) {
        this.expressionDao = expressionDao;
    }

    @Override
    public Expression saveToDB(Expression expression) {
        Expression savedExpression = expressionDao.save(expression);
        return savedExpression;
    }

    @Override
    public double calculation(String expression) {

        return new Object(){
            int position = -1;
            int ch;

            void nextChar() {
                if(++position < expression.length()) ch = expression.charAt(position);
                else ch = -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (position < expression.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            double parseExpression() {
                class Local {
                    double parseTerm() {
                        double x = parseFactor();
                        if  (eat('*')) x *= parseFactor();
                        else if (eat('/')) x /= parseFactor();
                        return x;
                    }
                }
                double x = new Local().parseTerm();
                if (eat('+')) x += new Local().parseTerm();
                else if (eat('-')) x -= new Local().parseTerm();
                return x;
            }

            double parseFactor() {
                if (eat('+')) return +parseFactor();
                if (eat('-')) return -parseFactor();
                double x;
                int startPos = this.position;
                if (eat('(')) {
                    x = parseExpression();
                    if (!eat(')')) throw new RuntimeException("Missing ')'");
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(expression.substring(startPos, this.position));
                } else throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }
        }.parse();

    }

    @Override
    public List<Expression> searchByExactResult(double query) {
        List<Expression> res = expressionDao.searchByExactResult(query);
        return res;
    }

    @Override
    public List<Expression> searchBiggerThan(double query) {
        List<Expression> res = expressionDao.searchBiggerThan(query);
        return res;
    }

    @Override
    public List<Expression> searchLessThan(double query) {
        List<Expression> res = expressionDao.searchLessThan(query);
        return res;
    }

}
