/**
 * @author Eckerstorfer Simon
 * Matrikelnummer: 11911424
 */

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public abstract class Fahrzeug implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public static final int thisYear = 2020;
	//private static List<Integer> allIds = new ArrayList<Integer>();		//only for in-main, not serialized
	private int id;
	private String marke;
	private String modell;
	private int baujahr;
	private double grundpreis;
	
	
	public Fahrzeug(int id, String ma, String mo, int bj, double gp) {
		//without try not 2 errors?
		//try {
			//if(allIds.contains(id)) throw new IllegalArgumentException("Error: Fahrzeug bereits vorhanden. (id=" + id + ")");	//only for in-main, not serialized
			if(ma.isEmpty()) throw new IllegalArgumentException("Error: Parameter ungueltig.");
			if(mo.isEmpty()) throw new IllegalArgumentException("Error: Parameter ungueltig.");
			if(bj > 2020) throw new IllegalArgumentException("Error: Baujahr ungueltig.");
			if(gp <= 0) throw new IllegalArgumentException("Error: Grundpreis ungueltig.");
			this.id = id;
			//allIds.add(id);
			marke = ma;
			modell = mo;
			baujahr = bj;
			grundpreis = gp;
		//}catch(Exception e) {
		//	System.out.println(e.getMessage());
		//}
	}
	
	public int getId() {
		return id;
	}
	public double getGrundpreis() {
		return grundpreis;
	}
	
	

	public int getAlter() {
		return (thisYear - this.baujahr);
	}
	
	public abstract double getRabatt();
	
	public double getPreis() {
		return (grundpreis - getRabatt());
	}
	
	public static DecimalFormat getDecimalFormat() {
		DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
		dfs.setDecimalSeparator('.');
		return new DecimalFormat("0.00", dfs);
	}

	@Override
	public String toString() {
		DecimalFormat df = Fahrzeug.getDecimalFormat();
		return "\nId:          " + id + "\nMarke:       " + marke + "\nModell:      " + modell + "\nBaujahr:     " + baujahr + "\nGrundpreis:  " + df.format(grundpreis);
	}		
}
