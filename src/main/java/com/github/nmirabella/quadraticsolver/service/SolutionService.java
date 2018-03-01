package com.github.nmirabella.quadraticsolver.service;

import com.github.nmirabella.quadraticsolver.model.Solution;
import org.springframework.stereotype.Service;


@Service
public class SolutionService {

    //Algorithm: https://math.stackexchange.com/a/311590
    public Solution solve(double a, double b, double c) {
        double r1;
        double r2;
        double discriminant = b * b - 4 * a * c;

        if (b < 0) {

            r1 = (-b + Math.sqrt(discriminant)) / (a + a);
            r2 = c / (a * r1);
        } else if (b > 0) {
            r1 = (-b - Math.sqrt(discriminant)) / (a + a);
            r2 = c / (a * r1);

        } else {
            r1 = Math.sqrt(c / a);
            r2 = -r1;
        }


        return new Solution(new double[]{r1, r2}, discriminant);
    }


}
