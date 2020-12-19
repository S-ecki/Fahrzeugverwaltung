import java.util.List;

/**
 * @author Eckerstorfer Simon
 * Matrikelnummer: 11911424
 */

public class FahrzeugClient {
	
	static FahrzeugManagement management;

	
	public static void main(String[] args) {	
		
		if(args.length >= 2) {		//only start if enough parameters
			management = new FahrzeugManagement(new SerializedFahrzeugDAO((args[0])));	
		}
		else return;
		
		
		switch(args[1]) {
		
		case "show":
			//show all
			if(args.length == 2) {
				List<Fahrzeug> allCars = management.getAll();
				for(Fahrzeug f: allCars) {
					System.out.println(f + "\n");
				}
			}else {
				//show one by id	
				System.out.println(management.getOne(Integer.parseInt(args[2])) + "\n");
			}
			break;
			
			
		case "add":
			try {
				Fahrzeug newF = null;
				
				//add lkw
				if(args[2].equals("lkw")) {
					if(args.length != 8) throw new IllegalArgumentException("Error: Parameter ungueltig.");		//false amount of parameters
					newF = new Lkw(Integer.parseInt(args[3]), args[4], args[5], Integer.parseInt(args[6]), Double.parseDouble(args[7]));
				}
				
				//add pkw
				else if(args[2].equals("pkw")) {
					if(args.length != 9) throw new IllegalArgumentException("Error: Parameter ungueltig.");		//false amount of parameters
					newF = new Pkw(Integer.parseInt(args[3]), args[4], args[5], Integer.parseInt(args[6]), Double.parseDouble(args[7]), Integer.parseInt(args[8]));
				}

				else {	
					//not pkw nor lkw
					throw new IllegalArgumentException("Error: Parameter ungueltig.");
				}
				
				management.addOne(newF);
				
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			break;
			
			
		case "del":
			//delete by id
			management.deleteOne(Integer.parseInt(args[2]));
			break;
			
			
		case "count":
			
			//count all
			if(args.length == 2) {
				System.out.println(management.count());
			}
			
			//count lkw
			else if(args[2].equals("lkw")) {
				System.out.println(management.countLkw());
			}
			
			//count pkw
			else if(args[2].equals("pkw")) {
				System.out.println(management.countPkw());
			}
				
			break;
			
		case "meanprice":
			System.out.println(management.avgPrice());
			break;
			
			
		case "oldest":
			for(Integer i: management.getOldest()) {
				System.out.println("Id: " + i);
			}
			break;
			
		case "service": 
           		List<Integer> list = management.service(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
            		for(Integer i : list) {
               			System.out.println("Id: " + i);
		    	}
            		break;
		
			
		default:
			System.out.println("Something went wrong.");
			
		}

	}
}
