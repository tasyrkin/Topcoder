package srm498_div2;

public class FoxSequence{
	public String isValid(int[] seq){
		int[] crl = {1,-1,0,1,-1};
		boolean isValid = true;
		int idx = 0;
		int diff = 0;
		for(int i = 0; i < seq.length-1; i++){
			if(i == 0){
				diff = seq[1]-seq[0];
			}
			if(i == 0 || diff != seq[i+1]-seq[i]){
				if(idx >= crl.length)
				{
					isValid = false;
					break;
				}
				diff = seq[i+1]-seq[i];
				if(diff*crl[idx] < 0){
					isValid = false;
					break;
				}
				else if(crl[idx] == 0 && diff*crl[idx] == 0){
					if(diff < 0){
						isValid = false;
						break;
					}
					else if(diff > 0){
						idx++;
					}
				}
				idx++;
			}
		}
		isValid = (idx != crl.length ? false : isValid);
		return isValid ? "YES" : "NO";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FoxSequence fs = new FoxSequence();
		//int[] seq = {1,3,5,7,5,3,1,1,1,3,5,7,5,3,1};
		//int[] seq = {3,6,9,1,9,5,1};
		//int[] seq = {1,2,3,2,1,2,3,2,1,2,3,2,1};		
		//int[]seq = {7, 8};
		int[]seq = {6, 9, 9, 12, 6};
		String res = fs.isValid(seq);
		System.out.println(res);
	}

}
