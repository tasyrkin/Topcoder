

public class CountExpressions {

	private static int value = 0;
	private static int[] numbers = new int[2];
	
	private int calcExpressions(int current, int ones, int twos, String currentExpression){
		
		if(ones>2 || twos>2){
			return 0;
		}
		int result = 0;
		if(ones == 2 && twos == 2){
			if(current==value){
				result++;
				//System.out.println(currentExpression);
			}
			return result;
		}
		
		for(int cnt = 0; cnt < numbers.length; cnt++){		
			result += calcExpressions(current+numbers[cnt], cnt==0?ones+1:ones, cnt==1?twos+1:twos, currentExpression + "+" + numbers[cnt]);
			result += calcExpressions(current-numbers[cnt], cnt==0?ones+1:ones, cnt==1?twos+1:twos, currentExpression + "-" + numbers[cnt]);
			result += calcExpressions(current*numbers[cnt], cnt==0?ones+1:ones, cnt==1?twos+1:twos, currentExpression + "*" + numbers[cnt]);
		}
		
		return result;
	}
	
	public int calcExpressions(int num1, int num2, int val){
		
		value = val;
		
		numbers[0] = num1;
		numbers[1] = num2;
		
		int result = 0;
		for(int cnt = 0; cnt < numbers.length; cnt++){		
			result+=calcExpressions(numbers[cnt], cnt==0?1:0, cnt==1?1:0, "" + numbers[cnt]);
		}
		return result;
	}
}
