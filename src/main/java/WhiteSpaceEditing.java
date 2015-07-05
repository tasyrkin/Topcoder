

import java.util.HashMap;
import java.util.Map;

public class WhiteSpaceEditing {
	
	public int getMinimum(int[] lines){		
		return getMinimum(lines, lines.length-1, lines[lines.length-1]) + lines[0];
	}
	Map<MapKey, Integer> cache = new HashMap<MapKey, Integer>();

	public static class MapKey{
		int level = -1;
		int srs = -1;
		
		public MapKey(int level, int srs){
			this.level = level;
			this.srs = srs;
		}
		
		public boolean equals(Object o){
			if(o != null && o instanceof MapKey){
				MapKey mk = (MapKey)o;
				return this.level == mk.level && this.srs == mk.srs;
			}
			return false;
		}
	
		public int hashCode(){
			return (srs << 0x7) & (0x7F & level);
		}
	}
	
	
	public int getMinimum(int[] lines, int j, int numSRs){
		
		if(j < 0){
			return 0;
		}
		int minCmds = Integer.MAX_VALUE;
		
		for(int currSRs = 0; currSRs < lines.length; currSRs++){
			Integer cachedValue = cache.get(new MapKey(j-1, lines[currSRs]));
			int currCmds = 0;
			if(cachedValue != null){
				currCmds = cachedValue;
			}
			else{
				currCmds = getMinimum(lines, j-1, currSRs) + (j == 0 ? 0 : 1);
				cache.put(new MapKey(j-1, currSRs), currCmds);
			}
			if(currCmds < minCmds){
				minCmds = currCmds;
			}
		}		
		return minCmds + Math.abs((lines[j]-numSRs));
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WhiteSpaceEditing wse = new WhiteSpaceEditing();		
		//int[] lines = { 3, 2, 3 };
		//int[] lines = { 0 };
		int[] lines = { 1, 2, 4 };
		//int[] lines = { 250, 105, 155, 205, 350 };
		int minimum = wse.getMinimum(lines);
		
		System.out.println("Minimum: " + minimum);
	}

}
