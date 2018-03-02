package com.github.nmirabella.quadraticsolver;

import com.github.nmirabella.quadraticsolver.model.Solution;
import com.github.nmirabella.quadraticsolver.service.SolutionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SolutionServiceTests {

    @Autowired
    SolutionService solutionService;

    @Test
    //b² − 4ac > 0 - If the discriminant is a perfect square the roots are rational.
    public void TwoRealSolutions(){
        BigDecimal a = BigDecimal.valueOf(1);
        BigDecimal b = BigDecimal.valueOf(-1);
        BigDecimal c = BigDecimal.valueOf(-2);
        Solution expectedResult = new Solution(new String[]{BigDecimal.valueOf(2).toString(), BigDecimal.valueOf(-1).toString()}, BigDecimal.valueOf(9));
        Solution calc = solutionService.solve(a, b, c);

        assertEquals(calc, expectedResult);
    }


    //b² − 4ac = perfect square -  Two Real Rational Solutions.
    //b² − 4ac = not perfect square -  Two Real irrational Solutions.

    @Test
    //b² − 4ac = 0 -  One Real Solution
    public void OneRealSolution(){
        BigDecimal a = BigDecimal.valueOf(1);
        BigDecimal b = BigDecimal.valueOf(2);
        BigDecimal c = BigDecimal.valueOf(1);
        Solution expectedResult = new Solution(new String[]{BigDecimal.valueOf(-1).toString()}, BigDecimal.ZERO);
        Solution calc = solutionService.solve(a, b, c);

        assertEquals(calc, expectedResult);

    }
    //b² − 4ac < 0 - No Real Solutions Two Imaginary Solutions
    @Test
    public void TwoImaginarySolutions(){
        BigDecimal a = BigDecimal.valueOf(5);
        BigDecimal b = BigDecimal.valueOf(2);
        BigDecimal c = BigDecimal.valueOf(1);
        Solution calc = solutionService.solve(a,b,c);
        Solution expectedResult = new Solution(new String[]{"-0.2 + 0.4i", "-0.2 - 0.4i"}, BigDecimal.valueOf(-16));

        assertTrue(calc.getDiscriminant().compareTo(BigDecimal.ZERO) < 0);
        assertEquals(calc,expectedResult);

    }

}
