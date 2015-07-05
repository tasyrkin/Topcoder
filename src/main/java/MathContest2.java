

public class MathContest2{
	public 	int countBlack(String ballSequence, int repetitions){
		char[]array = new char[ballSequence.length()*2];
		char[]temp = new char[ballSequence.length()];
		for(int i = 0; i < ballSequence.length(); i++){
			char ch = ballSequence.charAt(i);
			temp[i] = ch;
			array[i] = ch;
			array[i+temp.length] = ch;
		}
		int count = 0;
		boolean isStart = true;
		int start = 0;
		int end = 2*ballSequence.length()-1;
		if(repetitions==1){
			start = 0;
			end = ballSequence.length()-1;
			isStart = true;
			while(start<=end){
				if(isStart){
					if(array[start++]=='B'){
						count++;
						for(int i = start; i <= end; i++){
							if(array[i]=='B')array[i]='W';
							else array[i]='B';
						}						
					}
					else isStart = !isStart;
				}
				else{
					if(array[end--]=='B'){
						count++;
						for(int i = start; i <= end; i++){
							if(array[i]=='B')array[i]='W';
							else array[i]='B';
						}						
					}
					else isStart = !isStart;
				}			
			}
			return count;
		}		
		while(repetitions>0){			
			int changes = 0;
			while(start<ballSequence.length()&&end>ballSequence.length()-1){
				if(isStart){
					if(array[start++]=='B'){
						count++;
						changes++;
						for(int i = start; i <= end; i++){
							if(array[i]=='B')array[i]='W';
							else array[i]='B';
						}
					}
					else isStart = !isStart;
				}
				else{
					if(array[end--]=='B'){
						count++;
						changes++;
						for(int i = start; i <= end; i++){
							if(array[i]=='B')array[i]='W';
							else array[i]='B';
						}
					}
					else isStart = !isStart;
				}
			}
			if(changes%2==1){
				changes = 0;
				for(int i = 0; i < temp.length; i++){
					if(temp[i]=='B')temp[i]='W';
					else temp[i]='B';
				}
			}
			if(--repetitions==0){
				break;
			}
			if(start==ballSequence.length()){
				start = 0;
				for(int i = 0; i < ballSequence.length(); i++){
					array[i] = temp[i];
				}
			}
			else if(end==ballSequence.length()){
				end = 2*ballSequence.length()-1;
				for(int i = 0; i < ballSequence.length(); i++){
					array[i+ballSequence.length()] = temp[i];
				}
			}
		}
		while(start<=end){
			if(isStart){
				if(array[start++]=='B'){
					count++;
					for(int i = start; i <= end; i++){
						if(array[i]=='B')array[i]='W';
						else array[i]='B';
					}					
				}
				else isStart = !isStart;
			}
			else{
				if(array[end--]=='B'){
					count++;
					for(int i = start; i <= end; i++){
						if(array[i]=='B')array[i]='W';
						else array[i]='B';
					}					
				}
				else isStart = !isStart;
			}			
		}		
		return count;
	}
	public static void main(String[]args){
//		MathContest2 mc = new MathContest2();
//		int res = mc.countBlack("BW", 10);
//		System.out.println(res);
		System.out.println(5%2);
	}
	
}