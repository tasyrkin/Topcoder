package srm494_div2;

public class Painting{
	public int largestBrush(String[] picture){
		int[][] lang = new int[picture.length][picture[0].length()];
		for(int i=0;i<picture.length;i++){
			for(int j=0;j<picture[0].length();j++){
				if(picture[i].charAt(j)=='B')lang[i][j]=1;
				if(i==0||j==0)continue;
				if(picture[i-1].charAt(j)=='B'&&
				   picture[i].charAt(j-1)=='B'&&
				   picture[i-1].charAt(j-1)=='B'&&
				   picture[i].charAt(j)=='B'){
				   lang[i][j] = 1+Math.min(Math.min(lang[i-1][j],lang[i][j-1]),lang[i-1][j-1]);
				}
			}
		}
		int[][]clang = new int[lang.length][lang[0].length];
		int maxBrush = -1;
		for(int s=Math.min(lang.length,lang[0].length);s>1;s--){			
			for(int i=0;i<lang.length;i++)for(int j=0;j<lang[0].length;j++)clang[i][j] = lang[i][j];
			for(int i=clang.length-1;i>=s-1;i--){
				for(int j=clang[0].length-1;j>=s-1;j--){
					if(clang[i][j]==s){
						for(int i_c=i-s+1; i_c<=i;i_c++){
							for(int j_c=j-s+1; j_c<=j;j_c++){
								if(clang[i_c][j_c]==s)continue;
								clang[i_c][j_c] = 0;
							}
						}
					}
				}
			}
			boolean isValid = true;
			for(int i=clang.length-1;i>=0;i--){
				for(int j=clang[0].length-1;j>=0;j--){
					if(clang[i][j]>0&&clang[i][j]<s){
						isValid = false;
						break;
					}
				}
				if(!isValid)break;
			}
			if(isValid&&maxBrush==-1){
				maxBrush=s;
				break;
			}						
		}
		if(maxBrush==-1)maxBrush=1;
		return maxBrush;
	}
	
	public static void main(String[]args){
		Painting p = new Painting();
		String[] picture = {"BBBBBBBBBBBBW"
						  , "BBBBBBBBBBBBW"
						  , "BBBBBBBBBBBBW"
						  , "BBBBBBBBBBBBW"
						  , "BBBBBBBBBBBBW"
						  , "BBBBBBBBBBBBW"
						  , "BBBBBBBBBBBBW"
						  , "BBBBBBBBBBBBW"
						  , "BBBBBBBBBBBBW"
						  , "BBBBBBBBBBBBW"
						  , "BBBBBBBBBBBBW"
						  , "WWWBBBBBBBBWW"};
		int maxBrush = p.largestBrush(picture);
		System.out.println(maxBrush);
	}
}