package com.github.nmirabella.quadraticsolver.model;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Arrays;

public class Solution {

    @ApiModelProperty(value = "value depends on rootsComplex", required = true, dataType = "BigDecimal")
    private BigDecimal roots[];

    @ApiModelProperty(required = true, dataType = "BigDecimal")
    private BigDecimal discriminant;

    @ApiModelProperty(value = "If true, roots[] represents a ± bi where 'a' is roots[0] and 'b' is roots[1]." +
            "Otherwise, they are regular roots", required = true)
    private boolean isRootsComplex;

    public Solution(BigDecimal[] roots, BigDecimal discriminant, boolean isRootsComplex) {
        this.roots = roots;
        this.discriminant = discriminant;
        this.isRootsComplex = isRootsComplex;
    }

    public Solution(BigDecimal[] roots, BigDecimal discriminant) {
        this.roots = roots;
        this.discriminant = discriminant;
        this.isRootsComplex = false;
    }

    public BigDecimal[] getRoots() {
        return roots;
    }

    public void setRoots(BigDecimal[] roots) {
        this.roots = roots;
    }

    public BigDecimal getDiscriminant() {
        return discriminant;
    }

    public void setDiscriminant(BigDecimal discriminant) {
        this.discriminant = discriminant;
    }

    public boolean isRootsComplex() {
        return isRootsComplex;
    }

    public void setRootsComplex(boolean rootsComplex) {
        isRootsComplex = rootsComplex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Solution solution = (Solution) o;

        if (isRootsComplex != solution.isRootsComplex) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(roots, solution.roots) && discriminant.equals(solution.discriminant);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(roots);
        result = 31 * result + discriminant.hashCode();
        result = 31 * result + (isRootsComplex ? 1 : 0);
        return result;
    }
}
