

import java.util.*;

public class ComparerInator{
	public int makeProgram(int[] A, int[] B, int[] wanted){
		String first = "a";
		String second = "b";
		String third = "a<b?a:b";
		String forth = "a<b?b:a";
		HashSet<String> solution = new HashSet<String>();
		if(A[0]==wanted[0]){
			solution.add(first);
		}
		if(B[0]==wanted[0]){
			solution.add(second);
		}
		if((A[0]<B[0]?A[0]:B[0])==wanted[0]){
			solution.add(third);
		}
		if((A[0]<B[0]?B[0]:A[0])==wanted[0]){
			solution.add(forth);
		}
		if(solution.size()==0)return -1;
		for(int i = 1; i<A.length; i++){
			int exist = 0;
			if(A[i]==wanted[i]){
				if(solution.contains(first)){
					exist += 1;
				}				
			}
			if(B[i]==wanted[i]){
				if(solution.contains(second)){
					exist += 2;
				}				
			}
			if((A[i]<B[i]?A[i]:B[i])==wanted[i]){
				if(solution.contains(third)){
					exist += 4;
				}
			}
			if((A[i]<B[i]?B[i]:A[i])==wanted[i]){
				if(solution.contains(forth)){
					exist += 8;
				}
			}
			if(exist==0)return -1;
			if((exist&1)==0)solution.remove(first);
			if(((exist&2)>>1)==0)solution.remove(second);
			if(((exist&4)>>2)==0)solution.remove(third);
			if(((exist&8)>>3)==0)solution.remove(forth);
		}
		if(solution.size()==0)return -1;
		Iterator<String> iter = solution.iterator();
		int c = Integer.MAX_VALUE;
		while(iter.hasNext()){
			int currc = iter.next().length();
			if(currc < c)c = currc;
		}
		return c;
	}
	public static void main(String[] args){
		ComparerInator ci = new ComparerInator();
		int[] A = {1,3};
		int[] B = {2,1};
		int[] wanted = {2,3};
		int res = ci.makeProgram(A, B, wanted);
		System.out.println(res);
	}
}