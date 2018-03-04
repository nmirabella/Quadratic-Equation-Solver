package com.github.nmirabella.quadraticsolver.model;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

//https://en.wikipedia.org/wiki/Complex_number
public class ComplexNumber {
    @ApiModelProperty(value = "real part of the complex num", required = true, dataType = "object")
    private BigDecimal real;
    private BigDecimal imaginary;

    public ComplexNumber(BigDecimal real, BigDecimal imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public ComplexNumber(BigDecimal real) {
        this.real = real;
        this.imaginary = BigDecimal.ZERO;
    }

    public BigDecimal getReal() {
        return real;
    }

    public void setReal(BigDecimal real) {
        this.real = real;
    }

    public BigDecimal getImaginary() {
        return imaginary;
    }

    public void setImaginary(BigDecimal imaginary) {
        this.imaginary = imaginary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComplexNumber that = (ComplexNumber) o;

        return (real != null ? real.equals(that.real) : that.real == null) &&
                (imaginary != null ? imaginary.equals(that.imaginary) : that.imaginary == null);
    }

    @Override
    public int hashCode() {
        int result = real != null ? real.hashCode() : 0;
        result = 31 * result + (imaginary != null ? imaginary.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ComplexNumber{" +
                "real=" + real +
                ", imaginary=" + imaginary +
                '}';
    }
}
