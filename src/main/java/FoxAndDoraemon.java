
import java.util.*;
public class FoxAndDoraemon{
	public int minTime(int[] workCost, int splitCost){
		int n = workCost.length;
		if(n == 1)return workCost[0];
		Arrays.sort(workCost);
		int[] beg = new int[n];
		int[] dur = new int[n];
		int time = splitCost;
		for(int i = workCost.length-1; i >=0; i--){
			beg[workCost.length-1-i] = time;
			dur[workCost.length-1-i] = workCost[i];
			time += splitCost;
		}
		int max = 0;
		for(int i = 0; i < n; i++){
			if(beg[i]+dur[i]>max)max = beg[i]+dur[i];
		}

		time = workCost[workCost.length-1];
		for(int i = workCost.length-2; i >=0; i--){
			beg[workCost.length-1-i] = time;
			dur[workCost.length-1-i] = workCost[i];
			time += splitCost;
		}
		int max1 = 0;
		for(int i = 0; i < n-1; i++){
			if(beg[i]+dur[i]>max1)max1 = beg[i]+dur[i];
		}
		
		return Math.min(max,max1);
	}
	public static void main(String[] args) {
		FoxAndDoraemon f = new FoxAndDoraemon();
		int[] workCost = {1,2};
		int splitCost = 1000;
		f.minTime(workCost , splitCost );

	}

}
