

public class HowEasy {
	
	public int pointVal2(String arg){
		int lettersInAllWords = 0;
		int wordsNum = 0;
		String [] args = arg.split("\\s+");
		for(int cnt = 0; cnt < args.length; cnt++){
			char [] chars = new char[args[cnt].length()];
			args[cnt].getChars(0, args[cnt].length(), chars, 0);
			int currLettersNum = 0;
			for(int ch = 0; ch < chars.length; ch++){
				if(!Character.isLetter(chars[ch])){
					if(chars[ch]=='.' && ch==chars.length-1){
						if(currLettersNum>0){
							wordsNum++;
							lettersInAllWords+=currLettersNum+1;
							currLettersNum=0;
						}
					}
					else{
						currLettersNum = 0;
						break;
					}
				}
				else{
					currLettersNum++;
				}
			}
			if(currLettersNum>0){
				wordsNum++;
				lettersInAllWords+=currLettersNum;				
			}
		}
		int avg = lettersInAllWords / (wordsNum != 0 ? wordsNum : 1);
		if(avg<=3)return 250;
		else if(avg == 4 || avg == 5)return 500;
		else return 1000;
	}
}
