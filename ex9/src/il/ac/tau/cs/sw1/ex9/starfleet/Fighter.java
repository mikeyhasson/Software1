package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Fighter extends AbstarctCombatSpaceship {
	private final static int BASE_COST=2500;
	private final static int ENGINES_COST=1000;

	public Fighter(String name, int commissionYear, float maximalSpeed, Set<?extends CrewMember> crewMembers, List<Weapon> weapons)
	{
		super (name,commissionYear,maximalSpeed,crewMembers,weapons);
	}

	protected int getAnnualMaintenanceCost(int baseCost, int enginesCost) {
		return baseCost + weaponsCost + (int)(enginesCost * maximalSpeed);
	}
	
	public int getAnnualMaintenanceCost() {
		return  getAnnualMaintenanceCost(BASE_COST,ENGINES_COST);
	}
}
