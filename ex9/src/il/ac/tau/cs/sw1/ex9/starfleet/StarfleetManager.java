package il.ac.tau.cs.sw1.ex9.starfleet;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;


public class StarfleetManager {

	/**
	 * Returns a list containing string representation of all fleet ships, sorted in descending order by
	 * fire power, and then in descending order by commission year, and finally in ascending order by
	 * name
	 */
	public static List<String> getShipDescriptionsSortedByFirePowerAndCommissionYear (Collection<Spaceship> fleet) {
		return fleet.stream().sorted().map(x -> x.toString()).collect(Collectors.toList());
	}

	/**
	 * Returns a map containing ship type names as keys (the class name) and the number of instances created for each type as values
	 */
	public static Map<String, Integer> getInstanceNumberPerClass(Collection<Spaceship> fleet) {
		Map<String, Integer> map = new HashMap<>();
		fleet.stream()
		.map(x -> x.getClass().getSimpleName())
		.forEach(x -> addValueToCountsMap(map,x));
		return map;

	}


	/**
	 * Returns the total annual maintenance cost of the fleet (which is the sum of maintenance costs of all the fleet's ships)
	 */
	public static int getTotalMaintenanceCost (Collection<Spaceship> fleet) {
		return fleet.stream().mapToInt(x-> x.getAnnualMaintenanceCost()).sum();

	}


	/**
	 * Returns a set containing the names of all the fleet's weapons installed on any ship
	 */
	public static Set<String> getFleetWeaponNames(Collection<Spaceship> fleet) {
		Set <String> weaponsNames= new HashSet<>();
		fleet.stream()
			.filter(x -> x instanceof AbstarctCombatSpaceship)
			.map(x -> ((AbstarctCombatSpaceship)x).getWeapon())
			.forEach(x -> x.stream().map(y -> y.getName())
			.forEach(y -> weaponsNames.add(y)));
		return weaponsNames;
	}

	/*
	 * Returns the total number of crew-members serving on board of the given fleet's ships.
	 */
	public static int getTotalNumberOfFleetCrewMembers(Collection<Spaceship> fleet) {
		return fleet.stream().mapToInt(x -> x.getCrewMembers().size()).sum();

	}

	/*
	 * Returns the average age of all officers serving on board of the given fleet's ships. 
	 */
	public static float getAverageAgeOfFleetOfficers(Collection<Spaceship> fleet) {
		float sumOfAges=0;
		int officersCount=0;
		for (Spaceship ship:fleet) {
			int[] arr= ship.getCrewMembers().stream()
			.filter(x-> x instanceof Officer)
			.mapToInt(x -> x.getAge()).toArray();
			for (int i: arr)
				sumOfAges+=i;
			officersCount+=arr.length;
		}
		return sumOfAges/officersCount;
	}

	/*
	 * Returns a map mapping the highest ranking officer on each ship (as keys), to his ship (as values).
	 */
	public static Map<Officer, Spaceship> getHighestRankingOfficerPerShip(Collection<Spaceship> fleet) {
		Map<Officer, Spaceship> map = new HashMap<>();
		for (Spaceship ship:fleet) {
			Officer highest= ship.getCrewMembers().stream()
							.filter(x -> x instanceof Officer)
							.map(x -> (Officer)x)
							.max((x,y) -> x.getRank().compareTo(y.getRank()))
							.orElse(null);
			if (highest != null)
				map.put(highest, ship);
		}
		return map;
	}

	/*
	 * Returns a List of entries representing ranks and their occurrences.
	 * Each entry represents a pair composed of an officer rank, and the number of its occurrences among starfleet personnel.
	 * The returned list is sorted ascendingly based on the number of occurrences.
	 */
	public static List<Map.Entry<OfficerRank, Integer>> getOfficerRanksSortedByPopularity(Collection<Spaceship> fleet) {	
		Map<OfficerRank, Integer> map = new HashMap<>();
		for (Spaceship ship:fleet) {
			ship.getCrewMembers().stream()
			.filter(x -> x instanceof Officer)
			.map(x -> ((Officer)x).getRank())
			.forEach(x -> addValueToCountsMap(map,x));
			}
		List <Map.Entry<OfficerRank, Integer>> lst= new ArrayList<>(map.entrySet());
		lst.sort((x,y) -> comparator (x,y));
		return lst;
		}
	
	
	private static int comparator(Entry<OfficerRank, Integer> x, Entry<OfficerRank, Integer> y) {
		int c=x.getValue().compareTo(y.getValue());
		if (c!=0)
			return c;
		else 
			return x.getKey().compareTo(y.getKey());
	}

	private static <T> void addValueToCountsMap(Map<T, Integer> map, T x) {
		int value = map.get(x) == null ? 0 : map.get(x);
		map.put(x, value+1);
	}

}
