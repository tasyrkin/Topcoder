package srm499_div2;

import java.util.HashMap;
import java.util.Iterator;


public class ColorfulRabbits{
	public int getMinimum(int[] replies){
	
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(int cnt = 0; cnt < replies.length; cnt++){
			Integer elem = map.get(replies[cnt]);
			if(elem==null){
				elem = 1;
			}
			else{
				elem++;
			}
			map.put(replies[cnt],elem);
		}
		
		int min = 0;
		Iterator<Integer> iter = map.keySet().iterator();
		while(iter.hasNext()){
			int key = iter.next();
			int value = (Integer)map.get(key);
			int intPart = value/(key+1);
			int restPart = value%(key+1);
			if(restPart == 0){
				min += intPart*(key+1);
			}
			else{
				min += (intPart+1)*(key+1);
			}
			
		}
		return min;
	}
	
	public static void main(String[] args){
		ColorfulRabbits cr = new ColorfulRabbits();
		int[] replies = { 1, 1, 2, 2 };
		int result = cr.getMinimum(replies);
		System.out.println(result);
	}
}