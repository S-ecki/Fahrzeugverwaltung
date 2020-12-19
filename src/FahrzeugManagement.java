import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eckerstorfer Simon
 * Matrikelnummer: 11911424
 */

public class FahrzeugManagement {
	
	private FahrzeugDAO fahrzeugDAO;
	
	public FahrzeugManagement(FahrzeugDAO fahrzeugDAO) {
		this.fahrzeugDAO = fahrzeugDAO;
	}
	
	public List<Fahrzeug> getAll(){
		return fahrzeugDAO.getFahrzeugList();
	}
	
	public Fahrzeug getOne(int id) {
		return fahrzeugDAO.getFahrzeugbyId(id);
	}
	
	public void addOne(Fahrzeug f) {
		fahrzeugDAO.speichereFahrzeug(f);
	}
	
	public void deleteOne(int id) {
		fahrzeugDAO.loescheFahrzeug(id);
	}
	
	public int count() {
		return fahrzeugDAO.getFahrzeugList().size();
	}
	
	public int countLkw() {
		List<Fahrzeug> list = fahrzeugDAO.getFahrzeugList();
		int n = 0;
		for(Fahrzeug f: list) {
			if(f instanceof Lkw) ++n;
		}
		return n;
	}
	
	public int countPkw() {
		List<Fahrzeug> list = fahrzeugDAO.getFahrzeugList();
		int n = 0;
		for(Fahrzeug f: list) {
			if(f instanceof Pkw) ++n;
		}
		return n;
	}
	
	public String avgPrice() {
		DecimalFormat df = Fahrzeug.getDecimalFormat();
		List<Fahrzeug> list = fahrzeugDAO.getFahrzeugList();
		double sumPrice = 0;
		for(Fahrzeug f: list) {
			sumPrice += f.getGrundpreis() - f.getRabatt();
		}
		return df.format(sumPrice/list.size());
	}
	
	public List<Integer> getOldest() {
		List<Fahrzeug> list = fahrzeugDAO.getFahrzeugList();
		if(list.isEmpty()) return new ArrayList<Integer>();	//return  if list is empty - no nullptr error in next line
		
		Fahrzeug oldestF = list.get(0);		//determine oldest Fahrzeug
		for(Fahrzeug f: list) {			
			if(f.getAlter() > oldestF.getAlter()) oldestF = f;
		}
		
		List<Integer> oldestIds = new ArrayList<Integer>();		//fill list with ids from all oldest fahrzeuge
		for(Fahrzeug f: list) {
			if(f.getAlter() == oldestF.getAlter())	oldestIds.add(f.getId());
		}
		
		return oldestIds;
	}
	
	public List<Integer> service(int i, int j){
        List<Fahrzeug> input = fahrzeugDAO.getFahrzeugList();
        List<Integer> help = new ArrayList<Integer>();
        for(Fahrzeug f : input) {
            if(f instanceof Pkw && ((Pkw) f).getService() >= i && ((Pkw) f).getService() <= j) help.add(f.getId());
        }
        return help;
    }
	
}









