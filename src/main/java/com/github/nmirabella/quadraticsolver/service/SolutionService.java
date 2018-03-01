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
        double aa = a + a;

        if (discriminant < 0) { // support complex numbers by using regular quadratic equation
            String complexr1, complexr2;


            complexr1 = String.valueOf(-b / aa) + " + " + String.valueOf(Math.sqrt(Math.abs(discriminant)) / aa) + 'i';
            complexr2 = complexr1.replaceFirst("\\s\\+\\s", " - ");

            return new Solution(new String[]{complexr1, complexr2}, discriminant);

        }

        if (b < 0) {

            r1 = (-b + Math.sqrt(discriminant)) / aa;
            r2 = c / (a * r1);
        } else if (b > 0) {
            r1 = (-b - Math.sqrt(discriminant)) / aa;
            r2 = c / (a * r1);

        } else {
            r1 = Math.sqrt(c / a);
            r2 = -r1;
        }

        if (discriminant == 0)
            return new Solution(new String[]{String.valueOf(r1)}, discriminant);

        // sort the root array - it will make the results predictable
        if (r1 < r2) {
            double temp = r1;
            r1 = r2;
            r2 = temp;
        }
        return new Solution(new String[]{String.valueOf(r1), String.valueOf(r2)}, discriminant);


    }


}
