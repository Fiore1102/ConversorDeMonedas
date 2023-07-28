package Conversor;

public class ConversorDeTemperatura {
	
	    public static double celsiusToFahrenheit(double amountDouble){
	        return (amountDouble * 9/5) + 32;
	    };

	    public static double fahrenheitToCelsius(double amountDouble){
	        return (amountDouble - 32) * 5/9;
	    };
	}

