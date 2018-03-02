package com.github.nmirabella.quadraticsolver.service;

import com.github.nmirabella.quadraticsolver.model.Solution;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Service
public class SolutionService {


    //https://stackoverflow.com/a/16859436
    private static BigDecimal sqrt(BigDecimal value) {
        if (value.equals(BigDecimal.ZERO))
            return BigDecimal.ZERO;

        BigDecimal x = new BigDecimal(Math.sqrt(value.doubleValue()));
        return x.add(new BigDecimal(value.subtract(x.multiply(x)).doubleValue() / (x.doubleValue() * 2.0)));
    }

    public Solution solve(BigDecimal a, BigDecimal b, BigDecimal c) {

        BigDecimal discriminant = (b.multiply(b)).subtract(a.multiply(c).multiply(new BigDecimal(4)));
        BigDecimal aa = a.add(a);

        int compareTo = discriminant.compareTo(BigDecimal.ZERO);


        if (compareTo >= 0) {
            BigDecimal squareRoot = sqrt(discriminant);

            b = b.multiply(BigDecimal.valueOf(-1));


            BigDecimal r1 = b.add(squareRoot).divide(aa, RoundingMode.HALF_UP);

            if (compareTo == 0) // r1 == r2 in the case where discriminant is 0. Therefore only calculate r1.
                return new Solution(new String[]{r1.toString()}, discriminant);


            BigDecimal r2 = b.subtract(squareRoot).divide(aa, RoundingMode.HALF_UP);

            //sort roots array
            if (r1.compareTo(r2) > 0)
                return new Solution(new String[]{r1.toString(), r2.toString()}, discriminant);
            else
                return new Solution(new String[]{r2.toString(), r1.toString()}, discriminant);

        }


        // If we made it this far then discriminant < 0. Therefore the number is complex.
        String complexr1, complexr2;

        b = b.multiply(BigDecimal.valueOf(-1));
        complexr1 = String.valueOf(b.divide(aa, RoundingMode.HALF_UP)) +
                " + " +
                String.valueOf(sqrt(discriminant.abs()).divide(aa, RoundingMode.HALF_UP)) + 'i';

        complexr2 = complexr1.replaceFirst("\\s\\+\\s", " - ");

        return new Solution(new String[]{complexr1, complexr2}, discriminant);

    }


}
