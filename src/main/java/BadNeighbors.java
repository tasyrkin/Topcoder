import java.util.Arrays;

public class BadNeighbors{
    public int maxDonations(int[] d){
        int[][]dp = new int[d.length][2];
        dp[0][0] = 0;
        dp[0][1] = d[0];
        for(int i = 1; i < d.length; i++){
            int maxWF = 0;
            int maxWoF = 0;
            for(int j = 0; j < i-1; j++){
                if(maxWF < dp[j][1])maxWF = dp[j][1];
                if(maxWoF < dp[j][0])maxWoF = dp[j][0];
            }
            dp[i][1] = maxWF + (i == 1 || i == d.length-1 ? 0 : d[i]);
            dp[i][0] = maxWoF + d[i];
        }
        
        for(int i = 0; i < d.length; i++){
            System.out.println(i + ":" + Arrays.toString(dp[i]));
        }
        
        return Math.max(dp[d.length-1][1], dp[d.length-1][0]);
        //{90, 1, 2, 89, 3, 4, 88, 5, 6, 87, 5, 4, 86, 3, 2, 85, 1, 2}
    }

    public static void main(String[] args) {
        BadNeighbors bn = new BadNeighbors();
        
        System.out.println(bn.maxDonations(new int[]{90, 1, 2, 89, 3, 4, 88, 5, 6, 87, 5, 4, 86, 3, 2, 85, 1, 2}));
    }
}