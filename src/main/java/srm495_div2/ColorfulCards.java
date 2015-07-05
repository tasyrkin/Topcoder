package srm495_div2;

public class ColorfulCards {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int num = 1009;
		for(int i=1;i<=num;i++)if('R'==getColor(i))System.out.print(i + " ");
		//for(int i=2;i<=num/2;i++)if(num%i==0)System.out.println(i + " divides " + num);
	}

	public static char getColor(int num){
		if(num==1)return 'B';
		else if(num==2)return 'R';
		for(int i=2;i<=num/2;i++)if(num%i==0)return 'B';
		return 'R';
	}

}
