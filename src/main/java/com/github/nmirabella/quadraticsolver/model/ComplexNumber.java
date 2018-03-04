package com.github.nmirabella.quadraticsolver.model;

import java.math.BigDecimal;

//https://en.wikipedia.org/wiki/Complex_number
public class ComplexNumber {
    private BigDecimal real;
    private char sign;
    private BigDecimal imaginary;

    public ComplexNumber(BigDecimal real, char sign, BigDecimal imaginary) {
        this.real = real;
        this.sign = sign;
        this.imaginary = imaginary;
    }

    public BigDecimal getReal() {
        return real;
    }

    public void setReal(BigDecimal real) {
        this.real = real;
    }

    public char getSign() {
        return sign;
    }

    public void setSign(char sign) {
        this.sign = sign;
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

        return sign == that.sign && (real != null ? real.equals(that.real) : that.real == null)
                && (imaginary != null ? imaginary.equals(that.imaginary) : that.imaginary == null);
    }

    @Override
    public int hashCode() {
        int result = real != null ? real.hashCode() : 0;
        result = 31 * result + (int) sign;
        result = 31 * result + (imaginary != null ? imaginary.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "ComplexNumber{" +
                "real=" + real +
                ", sign=" + sign +
                ", imaginary=" + imaginary +
                '}';
    }
}
