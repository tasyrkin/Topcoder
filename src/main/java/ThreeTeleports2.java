
import java.util.*;
public class ThreeTeleports2 {
	public int shortestDistance(int xMe, int yMe, int xHome, int yHome, String[] teleports){
		String[] store = new String[8];
		store[0] = ""+xMe+","+yMe;
		store[7] = ""+xHome+","+yHome;
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		int cnt = 1;
		for(int i = 0; i < teleports.length; i++){
			String[]parts = teleports[i].split("\\s+");
			int p1 = cnt;
			store[cnt++] = parts[0]+","+parts[1];
			int p2 = cnt;
			store[cnt++] = parts[2]+","+parts[3];
			map.put(p1,p2);
			map.put(p2,p1);
		}
		long[][]dp = new long[8][8];
		for(int i = 0; i < dp.length; i++){
			String[]parts1 = store[i].split(",");
			for(int j = 0; j < dp[0].length; j++){
				if(i==j){
					dp[i][j] = 0;
					continue;
				}				
				String[]parts2 = store[j].split(",");
				if(map.get(i)!=null&&map.get(i)==j){
					dp[i][j] = 10;
				} else {
					dp[i][j] = Math.abs(Long.parseLong(parts1[0])-Long.parseLong(parts2[0]))+Math.abs(Long.parseLong(parts1[1])-Long.parseLong(parts2[1]));
				}
			}
		}		
		//solve(dp,7);
		for(int i = 0; i < dp.length; i++){
			for(int j = 0; j < dp[0].length; j++){
				if(i==j)continue;
				for(int k = 0; k < dp[0].length; k++){
					dp[i][j] = Math.min(dp[i][j],dp[i][k] + dp[k][j]);
				}
			}
		}		
		return (int)dp[0][7];
	}
	public long solve(long[][]dp, int j){
		if(j==0)return 0;
		long min = Long.MAX_VALUE;
		for(int k = 0; k < j; k++){
			long curr = solve(dp,k);
			if(curr<min)min = curr;
		}
		dp[0][j] = min;
		return dp[0][j];
	}
	/*public long solve(int i, int j, long[][]dp, int level){
		if(i==j)return 0;
		if(level>=3)return dp[i][j];
		long min = Long.MAX_VALUE;
		for(int k = 0; k < dp[0].length; k++){
			long curr = solve(i,k,dp,level+1) + solve(k,j,dp,level+1);
			if(curr<min)min = curr;
		}
		dp[i][j] = min;
		return dp[i][j];
	}*/
}