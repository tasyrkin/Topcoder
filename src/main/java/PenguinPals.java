public class PenguinPals{

    public int findMaximumMatching(String colors){
        char[]c = colors.toCharArray();
        int n = c.length;

        int[][]DP = new int[n][n];

        for(int s = 1; s < n; s++){
            for(int i = 0; i < n; i++){
                int prev = (c[i] == c[(i+s)%n] ? 1 : 0);
                if(s-2>=0){
                    prev += DP[(i+1)%n][s-2];
                }
                DP[i][s] = Math.max(DP[i][s-1], prev);
            }
        }
        int max = 0;
        for(int i = 0; i < n; i++){
            if(max < DP[i][n-1]){
                max = DP[i][n-1];
            }
        }

        return max;
    }
    public static void main(String[]args){
        PenguinPals pp = new PenguinPals();
        int res = pp.findMaximumMatching("RRRBBBRRRBBBRBRBRBRB");
        System.out.println(res);
    }
}