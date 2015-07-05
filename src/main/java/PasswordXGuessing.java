import java.util.*;
public class PasswordXGuessing{
	public long howMany(String[] guesses){
		boolean allEqual = true;
		for(int i = 0; i < guesses.length; i++){
			if(!guesses[i].equals(guesses[0])){
				allEqual = false;
				break;
			}
		}
		if(allEqual){
			return 9*guesses.length;
		}
		Set<Character>[] s = new HashSet[guesses[0].length()];
		for(int i = 0; i < guesses.length; i++){
			char[]arr = guesses[i].toCharArray();			
			for(int j = 0; j < arr.length; j++){
				if(s[j]==null){
					s[j] = new HashSet<Character>();
				}
				s[j].add(arr[j]);
			}
		}
		int moreThanOne = 0;
		int lastSize = 0;
		for(int i = 0; i <  s.length; i++){
			if(s[i].size()>1){
				moreThanOne++;
				lastSize = s[i].size(); 
			}
		}
		if(moreThanOne==1){
			return 10-lastSize;
		}
		long res = 0;
//		for(int i = 0; i < guesses.length; i++){
			char[]arr = guesses[0].toCharArray();
			for(int j = 0; j < arr.length; j++){
				chref:for(Character ch : s[j]){
					if(arr[j]==ch)continue;
					char[]arr1 = new char[guesses[0].length()];
					for(int k = 0; k < arr1.length; k++){
						if(k==j)arr1[k] = ch;
						else arr1[k] = arr[k];
					}
					for(int m = 0; m < guesses.length; m++){
						if(m==0)continue;
						char[]arr2 = guesses[m].toCharArray();
						int changed = 0;
						for(int k = 0; k < arr1.length; k++){
							if(arr2[k]!=arr1[k])changed++;
						}
						if(changed>1)continue chref;
					}
					res++;
				}
			}
//		}
		return res;
	}
	public static void main(String[] args) {
//		PasswordXGuessing p = new PasswordXGuessing();
//		String[] guesses = {"2", "3", "5"};
//		long res = p.howMany(guesses);
//		System.out.println(res);
		String s = "1";
		System.out.println(s.substring(1,1));

	}

}
