package srm497_div2;

import java.util.Arrays;
import java.util.HashMap;

import java.util.HashMap;

public class Filtering{
	public int[] designFilter(int[] sizes, String outcome){
		HashMap<Integer,String> map = new HashMap<Integer,String>();
		for(int i = 0; i<sizes.length;i++)map.put(sizes[i],""+outcome.charAt(i));
		Integer[] keys = map.keySet().toArray(new Integer[map.keySet().size()]);
		int A = -1;
		int B = -1;
		boolean acceptedMode = false;
		boolean rejectedMode = false;
		for(Integer i : keys){
			if(map.get(i).equals("A")){
				if(acceptedMode&&rejectedMode){
					int[] res = {};
					return res;
				}
				if(A==-1){
					A = i;
				}
				B = i;
				acceptedMode = true;
			}
			else{
				if(acceptedMode&&rejectedMode){
					int[] res = {};
					return res;
				}
				rejectedMode = true;
			}
		}		
		int[] res = {};
		if(acceptedMode){
			res = new int[2];
			res[0] = A;
			res[1] = B;
		}
		return res;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Filtering f = new Filtering();
		//int[] sizes = {3, 4, 5, 6, 7};
		//String outcome = "AAAAA";
		int[] sizes = {3, 4, 5, 6, 7};
		String outcome = "RAAAA";
		int [] res = f.designFilter(sizes, outcome);
		System.out.println("{" + (res.length != 0 ? "" + res[0] + res[1] : "") + "}");
		//System.out.println("{" + res[0] + res[1] + "}");
		
	}

}
