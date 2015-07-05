

import java.util.*;
public class CoinMachinesGame{
	public int maxGames(int coins, int[] need, int[] give){
		int n = need.length;
		int[]diff = new int[n];
		HashMap<Integer,ArrayList<Integer>> map = new HashMap<Integer,ArrayList<Integer>>();
		for(int i = 0; i < n; i++){
			diff[i] = need[i]-give[i];
			ArrayList<Integer> list = map.get(diff[i]);
			if(null==list){
				list = new ArrayList<Integer>();
			}
			list.add(need[i]);
			map.put(diff[i],list);
		}
		Arrays.sort(diff);
		int ans = 0;
		while(coins>0){
			int idx = -1;
			int found = -1;
			for(int i = 0; i < n; i++){
				ArrayList<Integer>list = map.get(diff[i]);
				found = -1;
				idx = -1;
				for(int c : list){
					if(coins-c>=0){
						found = c;
						idx = i;
						break;
					}
				}
				if(found!=-1)break;
			}		
			if(found==-1)return ans;	
			int currNPlays = (coins-found)/diff[idx];			
			if(currNPlays==0&&coins>=found){
				currNPlays = 1;
			}
			ans += currNPlays;
			coins -= currNPlays*diff[idx];
		}
		return ans;
	}
	public static void main(String[]args){
		CoinMachinesGame cmg = new CoinMachinesGame();
		int coins = 10;
		int[] need = {5,3};
		int[] give = {4,1};
		int ans = cmg.maxGames(coins , need , give );
		System.out.println(ans);
	}
}