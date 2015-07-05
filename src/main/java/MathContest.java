

public class MathContest{
	public int countBlack(String ballSequence, int repetitions){
		char[] arr = new char[ballSequence.length()*repetitions];
		int length = ballSequence.length();
		for(int i = 0; i < ballSequence.length()*repetitions; i++){
			arr[i] = ballSequence.charAt(length==1?0:i%length);
		}
		boolean isStart = true;
		int count = 0;
		int start = 0;
		int end = arr.length-1;
		while(start<end){
			if(isStart){
				if(arr[start++]=='B'){
					count++;
					for(int i = start; i <= end; i++){
						if(arr[i]=='W')arr[i]='B';
						else arr[i]='W';
					}
				}
				else{
					isStart = !isStart;
				}
			}
			else{
				if(arr[end--]=='B'){
					count++;
					for(int i = end; i >= start; i--){
						if(arr[i]=='W')arr[i]='B';
						else arr[i]='W';
					}
				}
				else{
					isStart = !isStart;
				}
			}
		}
		return count;
	}
	public static void main(String[]args){
		MathContest mc = new MathContest();
		int res = mc.countBlack("B", 3500);
		System.out.println(res);
	}
}