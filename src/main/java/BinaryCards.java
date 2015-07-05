
public class BinaryCards{
	public long largestNumber(long A, long B){
		if(A==B)return A;
		int bA = -1;
		int bB = -1;
		for(int i = 63; i>=0; i--){
			if((A>>i&1)==1&&bA==-1)bA=i;
			if((B>>i&1)==1&&bB==-1)bB=i;
		}
		int diffBit = 0;
		for(int i = bB; i >= 0; i--){			
			if(((A>>i)&1)!=((B>>i)&1)){
				diffBit = i;
				break;
			}
		}
		if(diffBit==63||(B>>63&1)==1){
			for(int i = bB-1; i >= 0; i--){			
				if(((A>>i)&1)!=((B>>i)&1)){
					diffBit = i;
					break;
				}
			}			
			long ret = 0;
			for(int i = 0; i <= diffBit; i++){
				ret |= (long)1<<i;
			}		
		} else {
			for(int i = 0; i <= diffBit; i++){
				B |= (long)1<<i;
			}		
		}	
		return B;		
	}
	public static void main(String[]args){
		BinaryCards bc = new BinaryCards();
		long res = bc.largestNumber(337007621073450791l, 337259621176923563l);
		System.out.println(res);
//		long t = (long)1<<49;
//		System.out.println(t);
	}
}