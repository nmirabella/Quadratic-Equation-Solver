package com.github.nmirabella.quadraticsolver;

import com.github.nmirabella.quadraticsolver.model.Solution;
import com.github.nmirabella.quadraticsolver.service.SolutionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class SolutionServiceTests {

    @Autowired
    SolutionService solutionService;

    private static final int scale = 10;

    private BigDecimal createNumber(String s) {
        return new BigDecimal(s).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    @Test
    //b² − 4ac > 0 - The roots are irrational.
    public void TwoRealSolutions(){
        BigDecimal a = createNumber("2");
        BigDecimal b = createNumber("5.55");
        BigDecimal c = createNumber("-3");

        Solution expectedResult = new Solution(new String[]{
                "0.4632177662",
                "-3.2382177662"},
                createNumber("54.8025"));

        Solution actual = solutionService.solve(a, b, c, scale);

        assertEquals(expectedResult, actual);
    }


    //b² − 4ac = perfect square -  Two Real Rational Solutions.
    @Test
    public void TwoRealRationalSolutions() {
        BigDecimal a = createNumber("1");
        BigDecimal b = createNumber("-1");
        BigDecimal c = createNumber("-2");

        Solution expectedResult = new Solution(new String[]{
                createNumber("2").toString(),
                createNumber("-1").toString()},
                createNumber("9"));

        Solution actual = solutionService.solve(a, b, c, scale);

        assertEquals(expectedResult, actual);

    }


    @Test
    //b² − 4ac = 0 -  One Real Solution
    public void OneRealSolution(){
        BigDecimal a = createNumber("1");
        BigDecimal b = createNumber("-2");
        BigDecimal c = createNumber("1");

        Solution expectedResult = new Solution(new String[]{
                createNumber("1").toString()},
                createNumber("0"));

        Solution actual = solutionService.solve(a, b, c, scale);

        assertEquals(expectedResult, actual);

    }
    //b² − 4ac < 0 - No Real Solutions Two Imaginary Solutions
    @Test
    public void TwoImaginarySolutions(){
        BigDecimal a = createNumber("5");
        BigDecimal b = createNumber("2");
        BigDecimal c = createNumber("1");

        Solution actual = solutionService.solve(a, b, c, scale);
        Solution expectedResult = new Solution(new String[]{
                createNumber("-0.2").toString() + " + " + createNumber("0.4") + "i",
                createNumber("-0.2").toString() + " - " + createNumber("0.4") + "i"}, createNumber("-16"));

        assertTrue(actual.getDiscriminant().compareTo(BigDecimal.ZERO) < 0);
        assertEquals(expectedResult, actual);

    }


}
