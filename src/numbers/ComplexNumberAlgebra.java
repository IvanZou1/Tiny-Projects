package numbers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

/*
 *
 * ComplexNumberAlgebra - The user enters 2 complex numbers (a + bi) by
 * entering numbers a and b, and this program output the results of addition,
 * subtraction, multiplication, division, negation, and inversion of the 2
 * complex numbers
 *
 */

public class ComplexNumberAlgebra {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Complex Number Algebra!");
        System.out.println("Enter the First Complex Number (a + bi): ");
        System.out.print("a = ");
        BigDecimal a1 = sc.nextBigDecimal();
        System.out.print("b = ");
        BigDecimal b1 = sc.nextBigDecimal();
        System.out.println("Enter the Second Complex Number (a + bi): ");
        System.out.print("a = ");
        BigDecimal a2 = sc.nextBigDecimal();
        System.out.print("b = ");
        BigDecimal b2 = sc.nextBigDecimal();
        Complex num1 = new Complex(a1, b1);
        Complex num2 = new Complex(a2, b2);
        printComplexNumber(num1);
        printComplexNumber(num2);
        printAddition(num1, num2);
        printSubtraction(num1, num2);
        printMultiplication(num1, num2);
        printDivision(num1, num2);
    }

    private static void printComplexNumber(Complex num) {
        System.out.println("__________________________________________________");
        System.out.println("Complex Number: " + num);
        System.out.print("Negation: " + num.negate());
        System.out.print(" | Conjugate: " + num.conjugate());
        System.out.println(" | Inversion: " + num.invert());
        System.out.println("__________________________________________________");
    }

    private static void printAddition(Complex num1, Complex num2) {
        System.out.println("Addition: ");
        System.out.print("(" + num1 + ") + (" + num2 + ")");
        System.out.println(" = " + num1.add(num2));
    }

    private static void printSubtraction(Complex num1, Complex num2) {
        System.out.println("Subtraction: ");
        System.out.print("(" + num1 + ") - (" + num2 + ")");
        System.out.println(" = " + num1.subtract(num2));
    }

    private static void printMultiplication(Complex num1, Complex num2) {
        System.out.println("Multiplication: ");
        System.out.print("(" + num1 + ")(" + num2 + ")");
        System.out.println(" = " + num1.multiply(num2));
    }

    private static void printDivision(Complex num1, Complex num2) {
        System.out.println("Division: ");
        System.out.print("(" + num1 + ") / (" + num2 + ")");
        System.out.println(" = " + num1.divide(num2));
    }
}

class Complex {
    BigDecimal real, imaginary;

    Complex(BigDecimal real, BigDecimal imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    Complex add(Complex number) {
        return new Complex(real.add(number.real), imaginary.add(number.imaginary));
    }

    Complex subtract(Complex number) {
        return this.add(number.negate());
    }

    Complex multiply(Complex number) {
        BigDecimal realReal = real.multiply(number.real);
        BigDecimal imagImag = imaginary.multiply(number.imaginary);
        BigDecimal realImag = real.multiply(number.imaginary);
        BigDecimal imagReal = imaginary.multiply(number.real);
        return new Complex(realReal.subtract(imagImag), realImag.add(imagReal));
    }

    Complex divide(Complex number) {
        Complex numerator = this.multiply(number.conjugate());
        Complex denominator = number.multiply(number.conjugate());
        return new Complex(numerator.real.divide(denominator.real, 5, RoundingMode.HALF_UP),
                numerator.imaginary.divide(denominator.real, 5, RoundingMode.HALF_UP));
    }

    Complex negate() {
        BigDecimal negative = BigDecimal.valueOf(-1);
        return new Complex(real.multiply(negative), imaginary.multiply(negative));
    }

    Complex invert() {
        Complex numerator = conjugate();
        Complex denominator = this.multiply(conjugate());
        return new Complex(numerator.real.divide(denominator.real, 5, RoundingMode.HALF_UP),
                numerator.imaginary.divide(denominator.real, 5, RoundingMode.HALF_UP));
    }

    Complex conjugate() {
        BigDecimal negative = BigDecimal.valueOf(-1);
        return new Complex(real, imaginary.multiply(negative));
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        double realDouble = real.setScale(5, RoundingMode.HALF_UP).doubleValue();
        double imaginaryDouble = imaginary.setScale(5, RoundingMode.HALF_UP).doubleValue();
        str.append(realDouble);
        boolean imagIsNegative = imaginaryDouble < 0;
        str.append(imagIsNegative ? " - " : " + ");
        str.append(imagIsNegative ? imaginaryDouble * -1 : imaginaryDouble);
        str.append("i");
        return str.toString();
    }
}
