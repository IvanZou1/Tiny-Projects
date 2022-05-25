package numbers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Scanner;

/*
 *
 * TemperatureConverter - The user first choose their starting temperature
 * system and enter the temperature value. Then, this program converts the
 * input temperature to the other two temperature systems and outputs the
 * conversion.
 *
 */

public class TemperatureConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Temperature Converter!");
        System.out.println("Choose Your Starting Temperature System:");
        System.out.println("Fahrenheit (Enter 0) | Celsius (Enter 1) | Kelvin (Enter 2)");
        System.out.print("Choice: ");
        int startTemp = sc.nextInt();
        if (startTemp == 0) {
            System.out.println("Fahrenheit: ");
            BigDecimal fahrenheit  = getTemperatureInput(sc);
            double celsius = fahrenheitToCelsius(fahrenheit);
            double kelvin = fahrenheitToKelvin(fahrenheit);
            printConversion(fahrenheit.doubleValue(), celsius, kelvin);
        } else if (startTemp == 1) {
            System.out.println("Celsius: ");
            BigDecimal celsius = getTemperatureInput(sc);
            double fahrenheit = celsiusToFahrenheit(celsius);
            double kelvin = celsiusToKelvin(celsius);
            printConversion(fahrenheit, celsius.doubleValue(), kelvin);
        } else if (startTemp == 2) {
            System.out.println("Kelvin: ");
            BigDecimal kelvin = getTemperatureInput(sc);
            double fahrenheit = kelvinToFahrenheit(kelvin);
            double celsius = kelvinToCelsius(kelvin);
            printConversion(fahrenheit, celsius, kelvin.doubleValue());
        } else {
            throw new IllegalArgumentException("Invalid Input: Please input 0, 1, or 2");
        }
        sc.close();
    }

    private static BigDecimal getTemperatureInput(Scanner sc) {
        System.out.print("Enter Temperature: ");
        return sc.nextBigDecimal().setScale(2, RoundingMode.HALF_UP);
    }

    private static double fahrenheitToCelsius(BigDecimal fahrenheit) {
        BigDecimal thirtyTwo = BigDecimal.valueOf(32);
        BigDecimal fiveByNine = BigDecimal.valueOf(0.555556);
        BigDecimal celsius = fahrenheit.subtract(thirtyTwo).multiply(fiveByNine);
        return celsius.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private static double fahrenheitToKelvin(BigDecimal fahrenheit) {
        double celsius = fahrenheitToCelsius(fahrenheit);
        double kelvin = celsius + 273.15;
        return BigDecimal.valueOf(kelvin).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private static double celsiusToFahrenheit(BigDecimal celsius) {
        BigDecimal nineByFive = BigDecimal.valueOf(1.8);
        BigDecimal thirtyTwo = BigDecimal.valueOf(32);
        BigDecimal fahrenheit = celsius.multiply(nineByFive).add(thirtyTwo);
        return fahrenheit.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private static double celsiusToKelvin(BigDecimal celsius) {
        BigDecimal twoSeventyThree = BigDecimal.valueOf(273.15);
        BigDecimal kelvin = celsius.add(twoSeventyThree);
        return kelvin.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private static double kelvinToFahrenheit(BigDecimal kelvin) {
        BigDecimal celsius = BigDecimal.valueOf(kelvinToCelsius(kelvin));
        return celsiusToFahrenheit(celsius);
    }

    private static double kelvinToCelsius(BigDecimal kelvin) {
        BigDecimal twoSeventyThree = BigDecimal.valueOf(273.15);
        BigDecimal celsius = kelvin.subtract(twoSeventyThree);
        return celsius.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private static void printConversion(double fahrenheit, double celsius, double kelvin) {
        System.out.println("__________________________________________________");
        System.out.println("Fahrenheit: " + formatTemp(fahrenheit) +  "\u00b0F");
        System.out.println("Celsius: " + formatTemp(celsius) + "\u00b0C");
        System.out.println("Kevin: " + formatTemp(kelvin) + "K");
        System.out.println("__________________________________________________");
    }

    private static String formatTemp(double temp) {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setGroupingUsed(true);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        return formatter.format(temp);
    }

}
