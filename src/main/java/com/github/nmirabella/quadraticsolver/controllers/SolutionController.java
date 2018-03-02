package com.github.nmirabella.quadraticsolver.controllers;

import com.github.nmirabella.quadraticsolver.Exceptions.InvalidScaleParameterException;
import com.github.nmirabella.quadraticsolver.Exceptions.NotQuadraticException;
import com.github.nmirabella.quadraticsolver.model.Solution;
import com.github.nmirabella.quadraticsolver.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1")
public class SolutionController {

    private final
    SolutionService solutionService;

    @Value("${scale.min}")
    private int scaleMin;

    @Value("${scale.max}")
    private int scaleMax;

    @Autowired
    public SolutionController(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    @RequestMapping("/solution")
    public Solution solution(@RequestParam(value = "a") BigDecimal a,
                             @RequestParam(value = "b") BigDecimal b,
                             @RequestParam(value = "c") BigDecimal c,
                             @RequestParam(value = "scale", defaultValue = "10", required = false) int scale) {

        if (!(scale >= scaleMin && scale <= scaleMax)) {
            throw new InvalidScaleParameterException(
                    "The parameter 'scale' must be >= " + scaleMin + " and " + " <= " + scaleMax);
        }

        a = a.setScale(scale, BigDecimal.ROUND_HALF_UP);
        b = b.setScale(scale, BigDecimal.ROUND_HALF_UP);
        c = c.setScale(scale, BigDecimal.ROUND_HALF_UP);

        if (a.equals(BigDecimal.ZERO.setScale(scale, BigDecimal.ROUND_HALF_UP)))
            throw new NotQuadraticException("The parameter 'a' cannot be zero");


        return solutionService.solve(a, b, c, scale);
    }
}
