import java.util.Arrays;

/**
 * @author  tasyrkin
 * @since   2014/04/26
 */
public class RedPaint {
    public static double expectedCells(final int N) {
        double[][] dp = new double[N + 2][N + 2];

        dp[0][1] = 1;
        dp[1][2] = 1;

        for (int i = 2; i <= N; i++) {

            for (int j = 2; j <= N + 1; j++) {
                dp[i][j] += dp[i - 1][j] / (double) 2;
                dp[i][j] += dp[i - 1][j - 1] / (double) 2;
            }

        }

        double res = 0;
        for (int j = 0; j <= N + 1; j++) {
            res += dp[N][j] * j;
        }

        System.out.println(res);

        for (int i = 0; i < dp.length; i++) {
            System.out.println("i=" + i + ": " + Arrays.toString(dp[i]));
        }

        return 0;

    }

    public static void main(final String[] args) {
        System.out.println(RedPaint.expectedCells(4));
    }
}
