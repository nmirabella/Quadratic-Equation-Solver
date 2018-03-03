package com.github.nmirabella.quadraticsolver.model;

import java.math.BigDecimal;
import java.util.Arrays;

public class Solution {

    private String roots[]; //must be string im order to support complex numbers
    private BigDecimal discriminant;

    public Solution(String[] roots, BigDecimal discriminant) {
        this.roots = roots;
        this.discriminant = discriminant;
    }

    public String[] getRoots() {
        return roots;
    }

    public void setRoots(String[] roots) {
        this.roots = roots;
    }

    public BigDecimal getDiscriminant() {
        return discriminant;
    }

    public void setDiscriminant(BigDecimal discriminant) {
        this.discriminant = discriminant;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "roots=" + Arrays.toString(roots) +
                ", discriminant=" + discriminant +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Solution solution = (Solution) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(roots, solution.roots)) return false;
        return discriminant.equals(solution.discriminant);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(roots);
        result = 31 * result + discriminant.hashCode();
        return result;
    }
}
