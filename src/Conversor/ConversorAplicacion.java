package Conversor;

import javax.swing.JOptionPane;

public class ConversorAplicacion {

	 private static final CambioDeDinero cambioDeDinero = new CambioDeDinero();
	    private static final CurrencyApi currencyApi = new CurrencyApi();

	    private static final ConversorDeTemperatura conversorDeTemperatura = new ConversorDeTemperatura();

	    public static void main(String[] args) {
	        moneyOrTemp();
	    }

	    public static void moneyOrTemp() {
	        String[] options = {"Cambio de Dinero", "Conversor de Temperatura"};
	        String input = (String) JOptionPane.showInputDialog(null, "Selecciona el tipo de Conversion",
	                "Menu", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

	        // Crear una declaración if para verificar que el usuario presiono la primera opción.
	        if (input.equals("Cambio de Dinero")) {
	            // el usuario ingresa la cantidad q va a cambiar
	            double amountDouble = amountOfMoney();

	            // el usuario selecciona el tipo de conversion
	            String conversionType = whatToChangeFromAndToMoney();
	            System.out.println(conversionType);
	            currencyApi.setCurrencies(conversionType);

	            // respuesta de la Api
	            double conversionRate = currencyApi.currencyApiRequest();

	            // el dinero es convertido y se muestra
	            moneyConversion(conversionType, amountDouble, conversionRate);

	            // Se le pregunta al usuario si quiere hacer otra conversion
	            askToContinue();

	        } else if (input.equals("Conversor de Temperatura")) {
	            // el usuario ingresa la cantidad a cambiar
	            double amountDouble = amountOfTemp();

	            // selecciona el tipo de conversion
	            String conversionType = whatToChangeFromAndToTemp();

	            // se convierte y muestra 
	            tempConversion(conversionType, amountDouble);

	            // Se le pregunta al usuario si quiere hacer otra conversion 
	            askToContinue();
	        }
	    }

	    public static double amountOfMoney() {
	        // Cree un método showInputDialog() para que el usuario ingrese la cantidad de dinero a convertir.
	        String amount = JOptionPane.showInputDialog("Ingresa la cantidad de dinero que deseas convertir");

	        //reemplace la coma con punto
	        amount = amount.replaceAll(",", ".");

	        // Cree un bloque de intento y captura para verificar si el usuario ha ingresado un número, con un contador para que el usuario pueda intentar hasta tres veces.
	        double amountDouble = 0;
	        int counter = 0;
	        int tries = 3;
	        while (counter < 3) {
	            try {
	                amountDouble = Double.parseDouble(amount);

	                // Valida si es un numero positivo
	                if (amountDouble < 0) {
	                    JOptionPane.showMessageDialog(null, "Por favor, ingresa un numero positivo");
	                } else if (amountDouble == 0) {
	                    JOptionPane.showMessageDialog(null, "Por favor, introduzca un numero mayor a 0");
	                } else {
	                    break;
	                }

	                amount = JOptionPane.showInputDialog("Enter the amount of money to be converted, you have " + (tries - counter) + " tries left");
	                counter++;
	            } catch (NumberFormatException e) {
	                amount = JOptionPane.showInputDialog("Please enter a valid number, you have " + (tries - counter) + " tries left");
	                counter++;
	            }
	        }

	        if (counter == 3) {
	            JOptionPane.showMessageDialog(null, "Ha superado el numero de intentos");
	            System.exit(0);
	        }

	        System.out.println(amountDouble);

	        return amountDouble;
	    }

	    public static double amountOfTemp() {
	        // Cree un método showInputDialog() para que el usuario ingrese la cantidad de dinero a convertir.
	        String amount = JOptionPane.showInputDialog("Enter the temperature number to be converted");

	        // reemplace coma por punto
	        amount = amount.replaceAll(",", ".");

	        //Cree un bloque try-catch para verificar si el usuario ha ingresado un número, con un contador
	        double amountDouble = 0;
	        int counter = 0;
	        int tries = 3;
	        while (counter < 3) {
	            try {
	                amountDouble = Double.parseDouble(amount);
	                counter++;
	                break;
	            } catch (NumberFormatException e) {
	                amount = JOptionPane.showInputDialog("Please enter a valid number, you have " + (tries - counter) + " tries left");
	                counter++;
	            }
	        }

	        if (counter == 3) {
	            JOptionPane.showMessageDialog(null, "You have exceeded the number of tries");
	            System.exit(0);
	        }

	        System.out.println(amountDouble);

	        return amountDouble;
	    }

	    public static String whatToChangeFromAndToMoney() {
	        String[] conversionOptions = {"Pesos Argentinos a Dolar", "Pesos Argentinos a Euro", 
	        "Pesos Argentinos a Libra", "Pesos Argentinos a Yuan", "Pesos Argentinos a Corean Won", 
	        "Dolar a Pesos Argentinos", "Euro a Pesos Argentinos", "Libra a Pesos Argentinos", 
	        "Yuan a Pesos Argentinos", "Corean Won a Pesos Argentinos"};


	        String conversionType = (String) JOptionPane.showInputDialog(null, "Selecciona el tipo de conversion", "Menu", JOptionPane.PLAIN_MESSAGE, null, conversionOptions, conversionOptions[0]);

	        // Swicth que pase del string largo a un acronimo
	        switch (conversionType) {
	            case "Pesos Argentinos a Dolar":
	                conversionType = "USD";
	                break;
	            case "Pesos Argentinos a Euro":
	                conversionType = "EUR";
	                break;
	            case "Pesos Argentinos a Libra":
	                conversionType = "GBP";
	                break;
	            case "Pesos Argentinos a Yuan":
	                conversionType = "CNY";
	                break;
	            case "Pesos Argentinos a Corean Won":
	                conversionType = "KRW";
	                break;
	            case "Dolar a Pesos Argentinos":
	                conversionType = "ARS";
	                currencyApi.setBaseCurrency("USD");
	                break;
	            case "Euro ta Pesos Argentinos":
	                conversionType = "ARS";
	                currencyApi.setBaseCurrency("EUR");
	                break;
	            case "Libra a Pesos Argentinos":
	                conversionType = "ARS";
	                currencyApi.setBaseCurrency("GBP");
	                break;
	            case "Yuan a Pesos Argentinos":
	                conversionType = "ARS";
	                currencyApi.setBaseCurrency("CNY");
	                break;
	            case "Corean Won a Pesos Argentinos":
	                conversionType = "ARS";
	                currencyApi.setBaseCurrency("KRW");
	            default:
	                throw new IllegalStateException("Unexpected value: " + conversionType);
	        }

	        return conversionType;
	    }

	    public static String whatToChangeFromAndToTemp() {
	        String[] conversionOptions = {"Celsius (C) a Fahrenheit (F)", "Fahrenheit (F) a Celsius (C)"};


	        String conversionType = (String) JOptionPane.showInputDialog(null, "Selecciona el tipo de conversion", "Menu", JOptionPane.PLAIN_MESSAGE, null, conversionOptions, conversionOptions[0]);

	        // Swicth que pase del string largo a un acronimo
	        switch (conversionType) {
	            case "Celsius (C) to Fahrenheit (F)":
	                conversionType = "CtoF";
	                break;
	            case "Fahrenheit (F) to Celsius (C)":
	                conversionType = "FtoC";
	                break;
	            default:
	                throw new IllegalStateException("Unexpected value: " + conversionType);
	        }

	        return conversionType;
	    }

	    public static void moneyConversion(String conversionType, double amountDouble, double conversionRate) {
	    	cambioDeDinero.setConversionRate(conversionRate);

	        if (conversionType.equals("ARS")) {
	            JOptionPane.showMessageDialog(null, "The amount of money in " + conversionType +
	                    " is: " + cambioDeDinero.toArs(amountDouble));
	        } else {
	            JOptionPane.showMessageDialog(null, "The amount of money in " + conversionType +
	                    " is: " + cambioDeDinero.arsTo(amountDouble));
	        }
	    }

	    public static void tempConversion(String conversionType, double amountDouble) {
	        if (conversionType.equals("CtoF")) {
	            JOptionPane.showMessageDialog(null, "The temperature in Fahrenheit is: " +
	            		conversorDeTemperatura.celsiusToFahrenheit(amountDouble) + "F°");
	        } else {
	            JOptionPane.showMessageDialog(null, "The temperature in Celsius is: " +
	            		conversorDeTemperatura.fahrenheitToCelsius(amountDouble) + "C°");
	        }
	    }

	    public static void askToContinue() {
	        int input = JOptionPane.showConfirmDialog(null, "Desea Continuar?", "Selecciona una opcion", JOptionPane.YES_NO_CANCEL_OPTION);

	        if (input == 0) {
	            moneyOrTemp();
	        } else if (input == 1) {
	            JOptionPane.showMessageDialog(null, "Programa Finalizado");
	            System.exit(0);
	        } else {
	            JOptionPane.showMessageDialog(null, "Programa Finalizado");
	            System.exit(0);
	        }
	    }
	}

