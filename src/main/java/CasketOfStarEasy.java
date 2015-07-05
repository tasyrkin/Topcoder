
import java.util.*;
public class CasketOfStarEasy{
	public static long max = 0;
	public int maxEnergy(int[] weight){
		Set<Integer> set = new HashSet<Integer>();
		for(int i = 1; i < weight.length-1; i++){
			set.add(i);
			maxEnergy(weight, set, (long)weight[i-1]*(long)weight[i+1]);
			set.remove(i);
		}		
		return (int)max;
	}
	public int maxEnergy(int[] weight, Set<Integer>set, long currMax){
		if(set.size()==weight.length-2){
			if(max<currMax)max = currMax;
			return 0;
		}
		for(int i = 1; i < weight.length-1; i++){
			if(set.contains(i))continue;
			int left = i-1;
			while(left>=0&&set.contains(left))left--;
			int right = i+1;	
			while(right<weight.length&&set.contains(right))right++;
			if(left<0||right>=weight.length)continue;
			set.add(i);
			maxEnergy(weight, set, currMax+(long)weight[left]*(long)weight[right]);
			set.remove(i);
		}
		return 0;
	}
	public static void main(String[] args) {
		CasketOfStarEasy c = new CasketOfStarEasy();
		int[] weight = {1,2,3,4};
		int res = c.maxEnergy(weight);
		System.out.println(res);
	}

}
