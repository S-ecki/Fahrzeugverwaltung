import java.text.DecimalFormat;

/**
 * @author Eckerstorfer Simon
 * Matrikelnummer: 11911424
 */

public class Lkw extends Fahrzeug {	

	private static final long serialVersionUID = 1L;

	public Lkw(int id, String ma, String mo, int bj, double gp) {
		super(id, ma, mo, bj, gp);
	}

	
	public double getRabatt() {		//Rabatt in Prozent
		double rabatt = 0;
		rabatt += getAlter() * 5;
		if(rabatt > 20) rabatt = 20;
		rabatt = getGrundpreis() * rabatt/100;
		return rabatt;		//Rabattwert
	}


	@Override
	public String toString() {
		DecimalFormat df = Fahrzeug.getDecimalFormat();
		return "Typ:         LKW" + super.toString() + "\nPreis:       " + df.format(this.getGrundpreis() - this.getRabatt());
	}
	
}
