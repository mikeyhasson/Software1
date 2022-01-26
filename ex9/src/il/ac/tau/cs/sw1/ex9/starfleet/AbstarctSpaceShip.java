package il.ac.tau.cs.sw1.ex9.starfleet;
import java.util.Set;

abstract class AbstarctSpaceship implements Spaceship,Comparable<Spaceship> {
	 static final int BASE_SPACESHIP_FIRE_POWER = 10;
	 int firePower;
	 String name; 
	 int commissionYear; 
	 float maximalSpeed;
	 Set<?extends CrewMember> crewMembers;

	public AbstarctSpaceship(String name, int commissionYear, float maximalSpeed, Set<?extends CrewMember> crewMembers)
	{
		this.name=name; 
		this.commissionYear=commissionYear; 
		this.maximalSpeed=maximalSpeed;
		this.crewMembers=crewMembers;	
		firePower=BASE_SPACESHIP_FIRE_POWER;
	}

	public String getName() {
		return name;
	}


	public int getCommissionYear() {
		return commissionYear;
	}

	public float getMaximalSpeed() {
		return maximalSpeed;
	}

	public int getFirePower() {
		return firePower;
	}
	

	public Set<?extends CrewMember> getCrewMembers() {
		return crewMembers;
	}
	
	public abstract int getAnnualMaintenanceCost();

	@Override
	public String toString() {
		return getClass().getSimpleName() + " \n\tName=" + name + "\n\tCommissionYear=" 
				+ commissionYear + "\n\tMaximalSpeed=" + maximalSpeed + "\n\tFirePower=" 
				+ firePower + "\n\tCrewMembers=" + crewMembers.size()
				+ "\n\tAnnualMaintenanceCost=" + getAnnualMaintenanceCost();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstarctSpaceship other = (AbstarctSpaceship) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	public int compareTo (Spaceship other) {
		int c = Integer.compare(firePower, other.getFirePower());
		if (c !=0)
			return (-1)*c;
		c=Integer.compare(commissionYear, other.getCommissionYear());
		if (c !=0)
			return (-1)*c;
		return name.compareTo(other.getName());
	}


}
