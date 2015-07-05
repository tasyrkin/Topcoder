package srm500;

import java.util.Arrays;

public class SRMCards2 {

	public int maxTurns(int[] cards){
		Arrays.sort(cards);
		
		int pre = -10;
		int ans = 0;
		for(int cur : cards){
			if(cur == pre+1){
				pre = -10;
			}
			else{
				++ans;
				pre = cur;
			}
		}
		return ans;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SRMCards2 srmcards2 = new SRMCards2();
		//int[] cards = {498, 499};
		//int[]cards = {491, 492, 495, 497, 498, 499};
		//int[]cards = {100, 200, 300, 400};
		//int[]cards = {11, 12, 102, 13, 100, 101, 99, 9, 8, 1};
		//int[]cards = {118, 321, 322, 119, 120, 320};
		int[]cards = {10, 11, 12, 13, 14, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		//int[]cards = {55, 52, 61, 204, 207, 54, 60, 202, 57, 58, 53, 210, 51, 59, 209, 205, 208, 201, 206, 211, 203, 56};
		int result = srmcards2.maxTurns(cards);
		System.out.println(result);
	}

}
