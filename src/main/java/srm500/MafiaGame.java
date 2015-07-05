package srm500;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MafiaGame {

	
	public void generateSubsets(int[]set, Set<Integer> subset, int length, Set<Set<Integer>> result){
		if(subset.size() == length){
			result.add(subset);
//			Iterator<Integer> iter = subset.iterator();
//			while(iter.hasNext()){
//				System.out.print(iter.next() + " ");
//			}
//			System.out.println("");
			return;
		}
		
		for(int cnt = 0; cnt < set.length; cnt++){
			if(!subset.contains(set[cnt])){
				HashSet<Integer> newSubset = new HashSet<Integer>();
				newSubset.add(set[cnt]);
				newSubset.addAll(subset);
				generateSubsets(set, newSubset, length, result);
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		MafiaGame mg = new MafiaGame();
		
		int[] set = {1,2,3,4,5,6,7,8,9};
		int length = 4;		
		Set<Set<Integer>> result = new HashSet<Set<Integer>>();
		mg.generateSubsets(set, new HashSet<Integer>(), length, result);
		
		for(Set<Integer> s : result){
			System.out.println(s.toString());
		}
		
	}

}
