import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eckerstorfer Simon
 * Matrikelnummer: 11911424
 */

public class SerializedFahrzeugDAO implements FahrzeugDAO {
	
	private String fileName;
	
	public SerializedFahrzeugDAO(String fn) {
		fileName = fn;
	}
	

	@Override
	@SuppressWarnings("unchecked")
	public List<Fahrzeug> getFahrzeugList() {
		
		List<Fahrzeug> fahrzeuge = new ArrayList<Fahrzeug>();
		try {
			ObjectInputStream reader = new ObjectInputStream(new FileInputStream(fileName));
			Object result = reader.readObject();
			
			if(!(result instanceof ArrayList)) {	//Object is Fahrzeug - add to list
				fahrzeuge.add((Fahrzeug) result);
			}else {									//Object is list
				fahrzeuge = (List<Fahrzeug>) result;
			}
			reader.close();
		}
		catch (Exception e) {
			System.err.println("Fehler bei Deserialisierung: " + e.getMessage());
			System.exit(1);
		}
		return fahrzeuge;
	}	
			
			

	@Override
	public Fahrzeug getFahrzeugbyId(int id) {
		List<Fahrzeug> list = getFahrzeugList();
		for(Fahrzeug f: list) {
			if(f.getId() == id) return f;
		}
		return null;
	}

	@Override
	public void speichereFahrzeug(Fahrzeug fahrzeug) {
		try {
			File file = new File(fileName);
			List<Fahrzeug> existent = new ArrayList<Fahrzeug>();
			
			if (file.exists()) {		//add fahrzeug to already saved fahrzeuge
				existent = getFahrzeugList();
				
				for(Fahrzeug f: existent) {
					if(f.getId() == fahrzeug.getId()) throw new IllegalArgumentException("Error: Fahrzeug bereits vorhanden. (id=" + f.getId() + ")");
				}

				existent.add(fahrzeug);
				file.delete();			//delete old file (without new fahrzeug)
			}else {		
				existent.add(fahrzeug);	//no fahrzeuge saved - list only with new fahrzeug
			}
			
			ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(fileName, true));	//create new persistent file with list
			writer.writeObject(existent);
			writer.close();

		}catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}catch (Exception e) {
				System.err.println("Fehler bei Serialisierung: " + e.getMessage());
				System.exit(1);
		}
	}

	@Override
	public void loescheFahrzeug(int id) {
		List<Fahrzeug> list = getFahrzeugList();
		boolean found = false;
		
		for(Fahrzeug f: list) {
			if(f.getId() == id) {	//if fahrzeug is found - delete
				found = true;
				list.remove(f);
				break;		//stop after found - else out of range error
			}
		}
		
		try {		//throw error if fahrzeug with id not found
			if(!found) {
				throw new IllegalArgumentException("Error: Fahrzeug nicht vorhanden. (id=" + id + ")");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		File file = new File(fileName);
		file.delete();						//delete old file containing deleted fahrzeug
		for(Fahrzeug newF: list) speichereFahrzeug(newF);	//save new list without deleted fahrzeug persistently
		
	}	
}
