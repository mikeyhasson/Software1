package il.ac.tau.cs.sw1.ex9.starfleet;

abstract class AbstractCrewMember  implements CrewMember {
	private int age;
	private int yearsInService;
	private String name;
	
	public AbstractCrewMember(int age, int yearsInService, String name){
		this.age=age;
		this.yearsInService=yearsInService;
		this.name=name;
	}

	public int getAge() {
		return age;
	}

	public int getYearsInService() {
		return yearsInService;
	}

	public String getName() {
		return name;
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
		AbstractCrewMember other = (AbstractCrewMember) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
