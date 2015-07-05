
import java.util.*;
public class FoxAndKgram{	
	public int maxK(int[] len){
		int max = Integer.MIN_VALUE;
		Arrays.sort(len);
		for(int l = 1; l <= 101; l++){
			Set<Integer> s = new HashSet<Integer>();
			int curr = 0;
			for(int i = 0; i < len.length; i++){
				if(l == len[i]){
					curr++;
					s.add(i);
				}
			}
			if(l<=curr&&max<curr){
				max = l;
			}
			for(int i = 0; i < len.length; i++){
				if(s.contains(i))continue;
				for(int j = 0; j < len.length; j++){
					if(i==j||s.contains(j)||s.contains(i))continue;
					if(len[i]+len[j]+1==l){
						s.add(i);
						s.add(j);
						curr++;
					}
				}
			}			
			if(l==curr&&max<curr){
				max = curr;			
			}
		}
		return max==Integer.MIN_VALUE?0:max;
	}
	public static void main(String[] args) {
		FoxAndKgram f = new FoxAndKgram();
		int[] len = {2, 2, 2, 2, 2, 2, 5};
		int r = f.maxK(len);
		System.out.println(r);
	}

}
