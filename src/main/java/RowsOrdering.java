
import java.util.*;
public class RowsOrdering{
	static class Pair implements Comparable  {
		char ch;
		int reps;
		public Pair(char ch, int reps){
			this.ch = ch;
			this.reps = reps;
		}
		public int compareTo(Pair p){
			int res = 0;
			if (this.reps<p.reps) res = -1;
			else if (this.reps>p.reps) res = 1;
			return res;
		}
		@Override
		public int compareTo(Object arg0) {
			if(arg0 != null && arg0 instanceof Pair){
				Pair p = (Pair)arg0;
				int res = 0;
				if (this.reps<p.reps) res = -1;
				else if (this.reps>p.reps) res = 1;
				return res;
			}
			return -1;
		}
	}
	public int order(String[] rows){
		char[][]chars = new char[rows.length][rows[0].length()];
		for(int i = 0; i < rows.length; i++){
			char[]row = rows[i].toCharArray();
			for(int j = 0; j < chars[0].length; j++){
				chars[i][j] = row[j];
			}
		}
		Map<Integer,List<Pair>> map = new HashMap<Integer,List<Pair>>();
		for(int j = 0; j < chars[0].length; j++){
			Map<Character,Integer> map2 = new HashMap<Character,Integer>();
			for(int i = 0; i < chars.length; i++){
				Integer reps = map2.get(chars[i][j]);
				if(reps==null){
					reps = 0;
				}
				reps++;
				map2.put(chars[i][j],reps);
			}
			Iterator<Character> iter = map2.keySet().iterator();
			List<Pair> pairs = new ArrayList<Pair>();
			while(iter.hasNext()){
				char ch = iter.next();
				int reps = map2.get(ch);
				Pair p = new Pair(ch,reps);
				pairs.add(p);
			}
			Collections.sort(pairs);
			Collections.reverse(pairs);
			map.put(j,pairs);
		}
		return 0;
	}
}