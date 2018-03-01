package com.github.nmirabella.quadraticsolver.model;

import java.util.Arrays;

public class Solution {

    private String roots[]; //must be string im order to support complex numbers
    private double discriminant;

    public Solution(String[] roots, double discriminant) {
        this.roots = roots;
        this.discriminant = discriminant;
    }

    public String[] getRoots() {
        return roots;
    }

    public void setRoots(String[] roots) {
        this.roots = roots;
    }

    public double getDiscriminant() {
        return discriminant;
    }

    public void setDiscriminant(double discriminant) {
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

        return Double.compare(solution.discriminant, discriminant) == 0 && Arrays.equals(roots, solution.roots);
    }

}
