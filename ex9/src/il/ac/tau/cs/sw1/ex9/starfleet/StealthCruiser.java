package il.ac.tau.cs.sw1.ex9.starfleet;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StealthCruiser extends Fighter {
	private static int stealthCruisersCounter;
	private final static int CLOAKING_DEVICE_COST=50;
	
	public StealthCruiser(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers){
		this (name,commissionYear,maximalSpeed,crewMembers,getDefaultWeapon());
	}
	
	
	private static List<Weapon> getDefaultWeapon() {
		List<Weapon> lst =new ArrayList<>();
		lst.add(new Weapon ("Laser Cannons",10,100));
		return lst;
	}

	public StealthCruiser(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, List<Weapon> weapons) {
		super (name,commissionYear,maximalSpeed,crewMembers,weapons);
		stealthCruisersCounter++;
	}

	public int getAnnualMaintenanceCost() {
		int cost = super.getAnnualMaintenanceCost();
		cost += stealthCruisersCounter * CLOAKING_DEVICE_COST;
		return cost;
		
	}

	
	

	
}
