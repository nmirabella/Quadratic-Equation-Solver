package com.github.nmirabella.quadraticsolver.service;

import com.github.nmirabella.quadraticsolver.Exceptions.NotQuadraticException;
import com.github.nmirabella.quadraticsolver.model.ComplexNumber;
import com.github.nmirabella.quadraticsolver.model.Solution;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Service
public class SolutionService {


    //https://stackoverflow.com/a/16859436
    private static BigDecimal sqrt(BigDecimal value, int scale) {
        if (value.equals(BigDecimal.ZERO.setScale(scale, RoundingMode.HALF_UP)))
            return BigDecimal.ZERO.setScale(scale, RoundingMode.HALF_UP);

        BigDecimal x = new BigDecimal(Math.sqrt(value.doubleValue())).setScale(scale, RoundingMode.HALF_UP);
        return x.add(new BigDecimal(value.subtract(x.multiply(x)).doubleValue() / (x.doubleValue() * 2.0))).
                setScale(scale, RoundingMode.HALF_UP);
    }

    public Solution solve(BigDecimal a, BigDecimal b, BigDecimal c, int scale) {

        if (a.equals(BigDecimal.ZERO.setScale(scale, RoundingMode.HALF_UP)))
            throw new NotQuadraticException("The parameter 'a' cannot be zero");


        BigDecimal discriminant = (b.multiply(b)).subtract(a.multiply(c).multiply(
                new BigDecimal(4))).setScale(scale, RoundingMode.HALF_UP);

        BigDecimal aa = a.add(a).setScale(scale, RoundingMode.HALF_UP);

        int compareTo = discriminant.compareTo(BigDecimal.ZERO);


        if (compareTo >= 0) {
            BigDecimal squareRoot = sqrt(discriminant, scale);

            b = b.multiply(BigDecimal.valueOf(-1));


            BigDecimal r1 = b.add(squareRoot).divide(aa, RoundingMode.HALF_UP).
                    setScale(scale, RoundingMode.HALF_UP);

            if (compareTo == 0) // r1 == r2 in the case where discriminant is 0. Therefore only calculate r1.
                return new Solution(new BigDecimal[]{r1, r1}, discriminant);


            BigDecimal r2 = b.subtract(squareRoot).divide(aa, RoundingMode.HALF_UP).
                    setScale(scale, RoundingMode.HALF_UP);

            //sort roots array
            if (r1.compareTo(r2) > 0)
                return new Solution(new BigDecimal[]{r1, r2}, discriminant);
            else
                return new Solution(new BigDecimal[]{r2, r1}, discriminant);

        }


        // If we made it this far then discriminant < 0. Therefore the number is complex.
        BigDecimal complexr1, complexr2;

        BigDecimal negativeb = b.multiply(BigDecimal.valueOf(-1)).setScale(scale, RoundingMode.HALF_UP);

        complexr1 = negativeb.divide(aa, RoundingMode.HALF_UP);

        complexr2 = sqrt(discriminant.abs(), scale).setScale(scale, RoundingMode.HALF_UP).
                divide(aa, RoundingMode.HALF_UP);


        return new Solution(new ComplexNumber[]{
                new ComplexNumber(complexr1, '+', complexr2),
                new ComplexNumber(complexr1, '-', complexr2)
        }, discriminant);

    }


}
