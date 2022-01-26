package il.ac.tau.cs.sw1.ex8.wordsRank;

import java.util.Comparator;
import il.ac.tau.cs.sw1.ex8.wordsRank.RankedWord.rankType;

class RankedWordComparator implements Comparator<RankedWord>{
	rankType cType;
	
	public RankedWordComparator(rankType cType) {
		this.cType=cType;
	}
	
	@Override
	public int compare(RankedWord o1, RankedWord o2) {
		int rankDiff =o1.getRankByType(cType)-o2.getRankByType(cType);
		if (rankDiff < 0)
			return -1;
		if (rankDiff>0)
			return 1;
		return 0;
	}	
}
