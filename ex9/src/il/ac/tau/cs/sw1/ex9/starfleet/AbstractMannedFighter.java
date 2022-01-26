package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

abstract class AbstractHomogenousFighter extends Fighter  {
	int baseCost;
	int enginesCost;
	int crewMemberCost;
	
	public AbstractHomogenousFighter(String name, int commissionYear, float maximalSpeed, Set<?extends CrewMember> crewMembers,
			List<Weapon> weapons,int baseCost, int enginesCost, int crewMemberCost) {
		super(name,commissionYear,maximalSpeed,crewMembers,weapons);
		this.baseCost=baseCost;
		this.enginesCost=enginesCost;
		this.crewMemberCost=crewMemberCost;
		
	}
	public int getAnnualMaintenanceCost() {
		int cost = getAnnualMaintenanceCost(baseCost,enginesCost);
		cost+= crewMemberCost * getCrewMembers().size();
		return cost;
	}
}
