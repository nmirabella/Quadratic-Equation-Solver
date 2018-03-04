package com.github.nmirabella.quadraticsolver.model;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Arrays;

public class Solution {

    @ApiModelProperty(value = "value is a pair of BigDecimals or a pair of Complex Numbers", required = true, dataType = "Number")
    private Object roots[];

    @ApiModelProperty(required = true, dataType = "BigDecimal")
    private BigDecimal discriminant;

    public Solution(ComplexNumber[] roots, BigDecimal discriminant) {
        this.roots = roots;
        this.discriminant = discriminant;

    }

    public Solution(BigDecimal[] roots, BigDecimal discriminant) {
        this.roots = roots;
        this.discriminant = discriminant;
    }

    public Object[] getRoots() {
        return roots;
    }

    public void setRoots(Object[] roots) {
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
        if (!Arrays.equals(roots, solution.roots)) return false;
        return discriminant != null ? discriminant.equals(solution.discriminant) : solution.discriminant == null;
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(roots);
        result = 31 * result + (discriminant != null ? discriminant.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "roots=" + Arrays.toString(roots) +
                ", discriminant=" + discriminant +
                '}';
    }
}
