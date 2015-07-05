package srm505_div2;

import java.math.BigInteger;

public class PerfectSequences {
	public String fixIt(int[] seq){
		if(seq.length==1)return "Yes";
		BigInteger[] arr = new BigInteger[seq.length];
		for(int i = 0; i < arr.length; i++)arr[i] = new BigInteger(""+seq[i]);		
		int avoid = 0;
		while(avoid<arr.length){
			BigInteger sum = BigInteger.ZERO;
			BigInteger prod = BigInteger.ONE;
			for(int i = 0; i < arr.length; i++){
				if(i==avoid)continue;
				sum = sum.add(arr[i]);
				prod = prod.multiply(arr[i]);				
			}
			System.out.println("avoid:"+avoid + ",s:" + sum + ",p:" + prod);
			if(sum.equals(BigInteger.ZERO)){
				if(prod.equals(BigInteger.ONE))return "Yes";
				else if(!arr[avoid].equals(BigInteger.ZERO))return "Yes";
			}
			prod = prod.subtract(BigInteger.ONE);
			//if(prod.equals(BigInteger.ONE))return "No";
			if(prod.compareTo(BigInteger.ZERO)>0
					&&sum.compareTo(prod)>=0
					&&sum.mod(prod).equals(BigInteger.ZERO)
					&&!sum.divide(prod).equals(arr[avoid])){
				return "Yes";
			}
			avoid++;
		}
		return "No";
	}
	
	public static void main(String[]args){
		PerfectSequences ps = new PerfectSequences();
		int[] seq = {1,3,4};
		System.out.println(ps.fixIt(seq));
	}

}
