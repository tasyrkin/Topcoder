
import java.util.*;
public class NetworkXOneTimePad2{
	public int crack(String[] plaintexts, String[] ciphertexts){
		System.out.println("n_plaintexts:" + plaintexts.length);
		System.out.println("n_ciphertexts:" + ciphertexts.length);
		int n = plaintexts[0].length();
		HashMap<String, HashSet<String>> keys = new HashMap<String, HashSet<String>>();
		for(String ct : ciphertexts){
			char[]ctArr = ct.toCharArray();
			for(String pt : plaintexts){
				char[]thisKey = new char[n];
				char[]ptArr = pt.toCharArray();
				for(int i = 0; i < n; i++){
					thisKey[i] = xor(ctArr[i],ptArr[i]);					
				}
				HashSet<String> keySet = keys.get(ct);
				if(keySet==null){
					keySet = new HashSet<String>();
				}
				keySet.add(new String(thisKey));
				keys.put(ct,keySet);
			}
		}
		int[] res = new int[ciphertexts.length];
		int cnt = 0;
		String ctFirst = null;
		HashSet<String>ctFirstSet = null;
		Iterator<String> iter = keys.keySet().iterator();
		while(iter.hasNext()){
			String curr = iter.next();
			HashSet<String> currSet = keys.get(curr);
			if(ctFirst==null){
				ctFirst = curr;
				ctFirstSet = currSet;
				res[cnt++] = ctFirstSet.size();
				continue;
			}
			int num = 0;
			for(String key : ctFirstSet){
				if(currSet.contains(key)){
					System.out.print(key + " ");
					num++;
				}
			}
			System.out.println();
			res[cnt++] = num;
		}
		System.out.println(Arrays.toString(res));
		Arrays.sort(res);
		return res[0];
	}
	public static char xor(char one, char two){
		return one == two ? '0' : '1';
	}
	
	public static void main(String[]args){
		NetworkXOneTimePad2 x = new NetworkXOneTimePad2();
		String[] plaintexts = {"001101001001100111", "001000111101100001", "001111001110100001", "000000111000010001", "110110001110000100", "000101100111101001", "001111100101101111", "100001011111011101", "010101010010010111", "110001011101000000", "000110001000011010", "111001110110000000", "110111010101100111", "100001011001101011", "111001111110110101", "001100111101011000", "110100101111010111", "111000010000111101", "011110101111111011", "101011100100100100", "110000100101011001", "101101011010011101", "101001100011001011", "111110111101110010", "010000000110010001", "011000011100101011", "001110101111110101", "110011010010110000", "111101010010101000", "110010111011010000", "100110011111001010", "101111011011011010", "101010100010100111", "110101110100100111", "010110101101111001", "111000010110001110", "011011111011011001", "011100100111101100", "110011110001111100", "101111100000111011", "101111000000111101", "101101001110110100", "010011110110101011", "001001000110110100"};
		String[] ciphertexts = {"010000010110101110", "100111100101001111", "101111010110011000", "010001011100110100", "110000010011111010", "010011001110011001", "010110110101110101", "000100001000011000", "111110101100011100", "001001011110111000", "110110010000001010", "101010000111100110", "011010010100101000", "001010100001010110", "110000101000011011", "110000110011111100", "110010111101110101", "101011011100010110", "011001111011011011", "101100000010111101", "100001001110110011", "100010100001101001"};
		int res = x.crack(plaintexts, ciphertexts);
		System.out.println(res);
	}
	
}