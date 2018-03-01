package com.github.nmirabella.quadraticsolver;

import com.github.nmirabella.quadraticsolver.model.Solution;
import com.github.nmirabella.quadraticsolver.service.SolutionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        double a = 1;
        double b = -1;
        double c = 2;
        Solution expectedResult = new Solution(new String[]{"-1.0", "2.0"}, 0 );

        assertEquals(solutionService.solve(a,b,c),expectedResult);
    }


    //b² − 4ac = perfect square -  Two Real Rational Solutions.
    //b² − 4ac = not perfect square -  Two Real irrational Solutions.

    @Test
    //b² − 4ac = 0 -  One Real Solution
    public void OneRealSolution(){
        double a = 1;
        double b = 2;
        double c = 1;
        Solution expectedResult = new Solution(new String[]{"-1.0"}, 0 );

        assertEquals(solutionService.solve(a,b,c),expectedResult);

    }
    //b² − 4ac < 0 - No Real Solutions Two Imaginary Solutions
    @Test
    public void TwoImaginarySolutions(){
        double a = 5;
        double b = 2;
        double c = 1;
        Solution calc = solutionService.solve(a,b,c);
        Solution expectedResult = new Solution(new String[]{"-0.2 + 0.4i", "-0.2 - 0.4i"}, -16 );

        assertTrue(calc.getDiscriminant() < 0);
        assertEquals(calc,expectedResult);

    }

}
