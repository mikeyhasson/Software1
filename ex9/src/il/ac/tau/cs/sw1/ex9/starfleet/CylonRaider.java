package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class CylonRaider extends AbstractHomogenousFighter  {
	private final static int BASE_COST=3500;
	private final static int ENGINES_COST=1200;
	private final static int CREW_MEMBER_COST=500;
	
	public CylonRaider(String name, int commissionYear, float maximalSpeed, Set<Cylon> crewMembers,
			List<Weapon> weapons) {
		super(name,commissionYear,maximalSpeed,crewMembers,weapons,BASE_COST,ENGINES_COST,CREW_MEMBER_COST);
		
	}
}
