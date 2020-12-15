import java.text.DecimalFormat;

/**
 * @author Eckerstorfer Simon
 * Matrikelnummer: 11911424
 */

public class Pkw extends Fahrzeug {
	
	private static final long serialVersionUID = 1L;
	private int service;
	
	public Pkw(int id, String ma, String mo, int bj, double gp, int service) {
		super(id, ma, mo, bj, gp);
		//without try not 2 errors?
		//try {
			if(service < bj) throw new IllegalArgumentException("Error: Servicejahr ungueltig.");
			this.service = service;
		//}catch(Exception e) {
		//	System.out.println(e.getMessage());
		//}
		
	}
	
	
	
	
	public double getRabatt() {		//Rabatt in Prozent gerechnet
		double rabatt = 0;
		
		rabatt += getAlter() * 5;
		rabatt += (thisYear - service) * 2;
		if(rabatt > 15) rabatt = 15;
		rabatt = getGrundpreis() * rabatt/100;
		return rabatt;		//Rabattwert
	}


	@Override
	public String toString() {
		DecimalFormat df = Fahrzeug.getDecimalFormat();
		if(this instanceof Epkw) return super.toString() + "\nServicejahr: " + service + "\nPreis:       " + df.format(this.getGrundpreis() - this.getRabatt());
		return "Typ:         PKW" + super.toString() + "\nServicejahr: " + service + "\nPreis:       " + df.format(this.getGrundpreis() - this.getRabatt());
	}




	public int getService() {
		return service;
	}
	
}
