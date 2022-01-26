package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

abstract class AbstarctCombatSpaceship extends AbstarctSpaceship {
	 List<Weapon> weapons;
	 int weaponsCost;

	public AbstarctCombatSpaceship(String name, int commissionYear, float maximalSpeed, Set<?extends CrewMember> crewMembers, List<Weapon> weapons)
	{
		super (name,commissionYear,maximalSpeed,crewMembers);
		this.weapons=weapons;
		for (Weapon w: weapons) {
			firePower+=w.getFirePower();
			weaponsCost+=w.getAnnualMaintenanceCost();
		}
	}
	
	public List<Weapon> getWeapon() {
		return weapons;
	}

	public String toString() {
		String s=super.toString()+ " \n\tWeaponArray=[";
		String separator="";
		for (Weapon w:weapons) {
			s+=separator + w.toString();
			separator=", ";
		}
		s+="]";
		return s;
		}

}
