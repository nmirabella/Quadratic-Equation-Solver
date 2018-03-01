package com.github.nmirabella.quadraticsolver.model;

import java.util.Arrays;

public class Solution {

    private double roots[];
    private double discriminant;

    public Solution(double[] roots, double discriminant) {
        this.roots = roots;
        this.discriminant = discriminant;
    }

    public double[] getRoots() {
        return roots;
    }

    public void setRoots(double[] roots) {
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
}
