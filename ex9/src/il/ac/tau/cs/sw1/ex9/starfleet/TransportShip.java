package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Set;

public class TransportShip extends AbstarctSpaceship {
	 final static int BASE_COST=3000;
	 final static int CARGO_COST_PER_MT=5;
	 final static int PASSENGER_COST=3;
	 int cargoCapacity;
	 int passengerCapacity;
	
	public TransportShip(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, int cargoCapacity, int passengerCapacity){
		super (name,commissionYear,maximalSpeed,crewMembers);
		 this.cargoCapacity=cargoCapacity;
		 this.passengerCapacity=passengerCapacity;
	}

	public int getCargoCapacity() {
		return cargoCapacity;
	}

	public int getPassengerCapacity() {
		return passengerCapacity;
	}

	public int getAnnualMaintenanceCost() {
		return BASE_COST + CARGO_COST_PER_MT * cargoCapacity + PASSENGER_COST * passengerCapacity;
	}

	@Override
	public String toString() {
		return super.toString() + "\n\tCargoCapacity=" + cargoCapacity + "\n\tPassengerCapacity="
				+ passengerCapacity;
	}
	
}
