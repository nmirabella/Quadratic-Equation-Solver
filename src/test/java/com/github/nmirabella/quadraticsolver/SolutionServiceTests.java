package com.github.nmirabella.quadraticsolver;

import com.github.nmirabella.quadraticsolver.Exceptions.NotQuadraticException;
import com.github.nmirabella.quadraticsolver.model.Solution;
import com.github.nmirabella.quadraticsolver.service.SolutionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
        return new BigDecimal(s).setScale(scale, RoundingMode.HALF_UP);
    }

    @Test(expected = NotQuadraticException.class)
    public void ExpectNotQuadradicException() {
        BigDecimal a = createNumber("0");
        BigDecimal b = createNumber("5.55");
        BigDecimal c = createNumber("-3");

        Solution actual = solutionService.solve(a, b, c, scale);
    }


    @Test
    //b² − 4ac > 0 - The roots are irrational.
    public void TwoRealSolutions(){
        BigDecimal a = createNumber("2");
        BigDecimal b = createNumber("5.55");
        BigDecimal c = createNumber("-3");

        Solution expectedResult = new Solution(new BigDecimal[]{
                createNumber("0.4632177662"),
                createNumber("-3.2382177662")},
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

        Solution expectedResult = new Solution(new BigDecimal[]{
                createNumber("2"),
                createNumber("-1")},
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

        Solution expectedResult = new Solution(new BigDecimal[]{
                createNumber("1")},
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
        Solution expectedResult = new Solution(new BigDecimal[]{
                createNumber("-0.2"), createNumber("0.4")}, createNumber("-16"), true);

        assertTrue(actual.getDiscriminant().compareTo(BigDecimal.ZERO) < 0);
        assertEquals(expectedResult, actual);

    }


}
