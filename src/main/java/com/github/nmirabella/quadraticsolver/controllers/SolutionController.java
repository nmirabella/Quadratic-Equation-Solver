package com.github.nmirabella.quadraticsolver.controllers;

import com.github.nmirabella.quadraticsolver.Exceptions.NotQuadraticException;
import com.github.nmirabella.quadraticsolver.model.Solution;
import com.github.nmirabella.quadraticsolver.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Solution solution(@RequestParam(value = "a") double a,
                             @RequestParam(value = "b") double b,
                             @RequestParam(value = "c") double c) {

        if (a == 0)
            throw new NotQuadraticException("The parameter 'a' cannot be zero");

        return solutionService.solve(a, b, c);
    }
}
