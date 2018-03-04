package com.github.nmirabella.quadraticsolver.model;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Arrays;

public class Solution {

    @ApiModelProperty(value = "value is a pair of ComplexNumber objects", required = true, dataType = "object")
    private ComplexNumber roots[];

    @ApiModelProperty(required = true, dataType = "BigDecimal")
    private BigDecimal discriminant;

    public Solution(ComplexNumber[] roots, BigDecimal discriminant) {
        this.roots = roots;
        this.discriminant = discriminant;

    }

    public Solution(BigDecimal[] roots, BigDecimal discriminant) {
        this.roots = new ComplexNumber[roots.length];

        for (int i = 0; i < roots.length; i++) {
            this.roots[i] = new ComplexNumber(roots[i]);
        }

        this.discriminant = discriminant;
    }

    public ComplexNumber[] getRoots() {
        return roots;
    }

    public void setRoots(ComplexNumber[] roots) {
        this.roots = roots;
    }

    public BigDecimal getDiscriminant() {
        return discriminant;
    }

    public void setDiscriminant(BigDecimal discriminant) {
        this.discriminant = discriminant;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Solution solution = (Solution) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(roots, solution.roots) && (discriminant != null ?
                discriminant.equals(solution.discriminant) : solution.discriminant == null);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(roots);
        result = 31 * result + (discriminant != null ? discriminant.hashCode() : 0);
        return result;
    }
}
