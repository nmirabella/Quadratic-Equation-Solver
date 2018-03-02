package com.github.nmirabella.quadraticsolver.controllers;

import com.github.nmirabella.quadraticsolver.Exceptions.NotQuadraticException;
import com.github.nmirabella.quadraticsolver.model.Solution;
import com.github.nmirabella.quadraticsolver.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1")
public class SolutionController {

    private final
    SolutionService solutionService;

    @Autowired
    public SolutionController(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    @RequestMapping("/solution")
    public Solution solution(@RequestParam(value = "a") BigDecimal a,
                             @RequestParam(value = "b") BigDecimal b,
                             @RequestParam(value = "c") BigDecimal c) {

        if (a.equals(BigDecimal.ZERO))
            throw new NotQuadraticException("The parameter 'a' cannot be zero");

        return solutionService.solve(a, b, c);
    }
}
