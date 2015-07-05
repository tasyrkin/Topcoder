package srm501_div2;

public class FoxAverageSequenceWrong {

    private static final int MOD = 1000000007;

    public int theCount(final int[] seq) {
        int n = seq.length;
        int maxSum = 40 * n + 1;
        long[][][][] dp = new long[n][maxSum][41][2];
        int st = seq[0] == -1 ? 0 : seq[0];
        int end = seq[0] == -1 ? 40 : seq[0];
        for (int i = st; i <= end; i++) {
            dp[0][i][i][0] = 0;
            dp[0][i][i][1] = 1;
        }

        for (int pos = 1; pos < n; pos++) {
            st = seq[pos] == -1 ? 0 : seq[pos];
            end = seq[pos] == -1 ? 40 : seq[pos];
            lelem:
            for (int nelem = st; nelem <= end; nelem++) {
                for (int sum = 0; sum < maxSum - nelem; sum++) {
                    if (sum < (pos - 1) * nelem) {
                        continue lelem;
                    }

                    for (int oelem = 0; oelem <= 40; oelem++) {
                        int nLessO = nelem < oelem ? 0 : 1;
                        if (nelem < oelem) {
                            dp[pos][sum + nelem][nelem][0] =
                                (dp[pos][sum + nelem][nelem][0] + dp[pos - 1][sum][oelem][1]) % MOD;
                        } else {
                            dp[pos][sum + nelem][nelem][1] =
                                (dp[pos][sum + nelem][nelem][1] + dp[pos - 1][sum][oelem][1]) % MOD;
                            dp[pos][sum + nelem][nelem][1] =
                                (dp[pos][sum + nelem][nelem][1] + dp[pos - 1][sum][oelem][0]) % MOD;
                        }
                    }
                }
            }
        }

        long res = 0;
        for (int elem = 0; elem <= 40; elem++) {
            for (int sum = 0; sum < maxSum; sum++) {
                res = (res + dp[n - 1][sum][elem][0]) % MOD;
                res = (res + dp[n - 1][sum][elem][1]) % MOD;
            }
        }

        return (int) res;
    }

    public static void main(final String[] args) {
        System.out.println(new FoxAverageSequenceWrong().theCount(new int[] {3, -1}));
    }

}
