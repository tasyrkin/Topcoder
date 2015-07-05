

import java.util.*;
public class CandyShop{
	public int countProbablePlaces(int[] X, int[] Y, int[] R){
		int n = X.length;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(int i = 0; i<n; i++){
			countProbablePlaces(X[i], Y[i], R[i], map);
		}
		Iterator<String> iter = map.keySet().iterator();
		HashSet<String> res = new HashSet<String>();
		while(iter.hasNext()){
			String key = iter.next();
			Integer num = map.get(key);
			if(num==n){
				res.add(key);
			}
		}
		return res.size();
	}
	public int countProbablePlaces(int x, int y, int r, HashMap<String, Integer> map){
		int deltay = -1;
		for(int i = x-r; i<=x+r; i++){
			if(i>x)deltay--;
			else deltay++;
			for(int j = -deltay; j <= deltay; j++){
				int y_1 = y+j;
				Integer num = map.get(i+","+y_1);
				if(num==null){
					num = 0;
				}
				num++;
				map.put(i+","+y_1,num);
			}
		}
		return 0;
	}
	public static void main(String args[]){
		CandyShop ch = new CandyShop();
		int[] X = {0};
		int[] Y = {0};
		int[] R = {1};
		int res = ch.countProbablePlaces(X, Y, R);
		System.out.println(res);
		
	}

}