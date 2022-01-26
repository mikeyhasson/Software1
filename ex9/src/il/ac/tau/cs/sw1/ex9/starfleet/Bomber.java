package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Bomber extends AbstarctCombatSpaceship{
	private final static int BASE_COST=5000;
	int numberOfTechnicians;

	public Bomber(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, List<Weapon> weapons, int numberOfTechnicians){
			super (name,commissionYear,maximalSpeed,crewMembers,weapons);
			this.numberOfTechnicians=numberOfTechnicians;
		}

		public int getAnnualMaintenanceCost() {
			int weaponCostWithDiscount = (int)(weaponsCost * ((10 - numberOfTechnicians)/10.0));
			return BASE_COST +weaponCostWithDiscount;
		}
	public String toString() {
		return super.toString() + " \n\tNumberOfTechnicians=" + numberOfTechnicians;
	}
	}
