
import java.util.*;
public class EllysJuice{
	private Set<String> set = new HashSet<String>();
	public String[] getWinners(String[] players){
		List<String> pl = new ArrayList<String>();
		for(int i = 0; i < players.length; i++){
			pl.add(players[i]);
		}
		solve(pl, 0, new ArrayList<String>());
		if(set.size()==0)return new String[0];
		String[] res = set.toArray(new String[set.size()]);
		Arrays.sort(res);
		return res;
	}
	public void solve(List<String> players, int i, List<String> res){
		if(players.size()==0){
			int j = 0;
			Map<String, Double> map2 = new HashMap<String, Double>();
			double max = 0.0;
			for(String s : res){
				Double d = map2.get(s);
				if(d == null){
					d = 0.0;
				}
				int step = j/2;
				if(step < 0)step = 0;
				d += (double)1/(2*Math.pow(2,step));
				if(max < d)max = d;
				map2.put(s, d);		
				j++;
			}
			int cnt = 0;
			String win = null;
			for(String s : map2.keySet()){
				if(map2.get(s) == max){
					cnt++;
					win = s;
				}
			}
			if(cnt == 1){
				set.add(win);
			} 
		}
		for(int j = 0; j < players.size(); j++){
			String s = players.remove(j);
			res.add(s);
			solve(players, i+1, res);
			res.remove(res.size()-1);
			players.add(j, s);
		}
	}
	public static void main(String[] args) {
		EllysJuice e = new EllysJuice();
		String[] players = {"torb", "elly", "stancho", "kriss"};
		String [] res = e.getWinners(players);
		System.out.println(Arrays.toString(res));
	}

}
