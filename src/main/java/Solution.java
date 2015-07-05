
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

	public static class Pair implements Comparable{
		int b;
		int s;
		int i;
		public Pair(int b, int s, int i){
			this.b = b;
			this.s = s;
			this.i = i;
		}
		@Override
		public int compareTo(Object arg0) {
			Pair another = (Pair)arg0;
			if(another.s>this.s&&another.b>this.b){
				return -1;
			}
			else if(another.s<this.s&&another.b<this.b){
				return 1;
			}
			return 0;
		}
		public String toString(){
			return "(" + b + "," + s + "," + i + ")";
		}
	}
	
	public static int[]cache = null;
	public static int[]prevs = null;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = -1;
		Pair[]a = null;
		String line = null;
		while(null != (line = br.readLine())){
			if(n==-1){
				n = Integer.parseInt(line);
				a = new Pair[n];
				cache = new int[n];
				tmp = new Pair[n];
				prevs = new int[n+1];
				continue;
			}
			String[]parts = line.split("\\s+");
			n--;
			int idx = a.length-n-1;
			a[idx] = new Pair(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),idx+1);
			if(n<=0)break;
		}
//		for(int i = 0; i < a.length; i++){
//			System.out.print(a[i] + " ");
//		}
//		System.out.println();
		sort(a, 0, a.length-1);
//		for(int i = 0; i < a.length; i++){
//			System.out.print(a[i] + " ");
//		}
//		System.out.println();
		int max = Integer.MIN_VALUE;
		for(int i = 0; i <= a.length-1; i++){
			int curr = solve(a,i); 
			if(curr>max){
				max = curr;
				prevs[prevs.length-1] = i;
			}
		}
		System.out.println(max);
		printChain(a);
////		for(int i = 0; i < prevs.length; i++){
////			System.out.print(prevs[i] + " ");
////		}
////		
//		for(int i = 0; i < cache.length; i++){
//			System.out.print(cache[i] + " ");
//		}
//		System.out.println();
	}
	
	public static void printChain(Pair[]a){
		int idx = prevs[prevs.length-1];
		while(idx>=0){
			System.out.print(a[idx].i + " ");
			idx = prevs[idx];
		}
		System.out.println();
	}
	
	public static int solve(Pair[]a, int j){
		if(cache[j]>0)return cache[j]; 
		int max = 1;
		int idx = -1;
		for(int i = 0; i < a.length; i++){
			if(a[i].compareTo(a[j])<0){
				int curr = solve(a,i);
				if(max<curr+1){
					max = curr+1;
					idx = i;
				}
			}
//			else if(i==j)break;
//			else if(a[i].compareTo(a[j])>0){
//				break;
//			}
		}
		cache[j] = max;
		prevs[j] = idx;
		return max;
	}
	
	public static Pair[]tmp = null;
	public static void sort(Pair[]a, int i, int j){
		if(i>=j)return;
		int middle = i+(j-i)/2;
//		System.out.println("i:" + i + ",j:" + j + ",middle:" + middle);
		sort(a,i,middle);
		sort(a,middle+1,j);
		for(int idxLeft=i,idxRight=middle+1,index=i;true;){
			if(idxLeft>middle&&idxRight>j)break;
			if(idxLeft>middle){
				tmp[index++] = a[idxRight++];
			}
			else if(idxRight>j){
				tmp[index++] = a[idxLeft++];
			}
			else{
				if(a[idxLeft].compareTo(a[idxRight])<0){
					tmp[index++] = a[idxLeft++]; 
				}
				else if(a[idxRight].compareTo(a[idxLeft])<0){
					tmp[index++] = a[idxRight++];
				}
				else{
					tmp[index++] = a[idxLeft++];
					tmp[index++] = a[idxRight++];
				}
			}
		}
		for(int index = i; index <= j; index++){
			a[index] = tmp[index];
		}
	}
}
