package Conversor;

public class CambioDeDinero {

	  private double conversionRate;
	    public void setConversionRate(double conversionRate) {
	         this.conversionRate = conversionRate;
	    }

	    public double arsTo(double amount) {
	        return amount * conversionRate;
	    }

	    public double toArs(double amount) {
	        return amount * conversionRate;
	    }
	}

