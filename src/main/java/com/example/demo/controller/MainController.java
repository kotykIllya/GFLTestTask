package com.example.demo.controller;

import com.example.demo.entity.Expression;
import com.example.demo.service.ExpressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.script.ScriptException;
import java.util.List;

@Controller
@RequestMapping("/index")
public class MainController {

    private final ExpressionService expressionService;

    @Autowired
    public MainController(ExpressionService expressionService) {
        this.expressionService = expressionService;
    }

    @PostMapping("/saveExpression")
    public String saveExpression(Model model, @RequestParam String expression) throws ScriptException {
        Expression expression1 = new Expression();
        expression1.setResult(expressionService.calculation(expression));
        expression1.setFullExpression(expression);
        Expression savedExpression = expressionService.saveToDB(expression1);
        model.addAttribute("expression", savedExpression);
        return "index";
    }

    @GetMapping("/searchExactResult/")
    public String searchByExactResult(Model model, @RequestParam double query) {
        List<Expression> expressions = expressionService.searchByExactResult(query);
        model.addAttribute("expressions", expressions);
        return "searchPageExactResult";
    }

    @GetMapping("/searchBiggerThan/")
    public String searchBiggerThan(Model model, @RequestParam double query) {
        List<Expression> expressions = expressionService.searchBiggerThan(query);
        model.addAttribute("expressions", expressions);
        return "searchPageBiggerThan";
    }

    @GetMapping("/searchLessThan/")
    public String searchLessThan(Model model, @RequestParam double query) {
        List<Expression> expressions = expressionService.searchLessThan(query);
        model.addAttribute("expressions", expressions);
        return "searchPageLessThan";
    }

}
