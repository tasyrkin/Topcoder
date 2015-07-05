/**
 * @author  tasyrkin
 * @since   2014/01/21
 */
public class AlienAndSetDiv2 {
    public static final int MOD = 1000000007;

    static int[] gcd(final int p, final int q) {
        if (q == 0) {
            return new int[] {p, 1, 0};
        }

        int[] vals = gcd(q, p % q);
        int d = vals[0];
        int a = vals[2];
        int b = vals[1] - (p / q) * vals[2];
        return new int[] {d, a, b};
    }

    private int fact(final int N) {
        int res = N;
        for (int i = 1; i <= N - 1; i++) {
            res = (res * i) % MOD;
        }

        return res;
    }

    public int getNumber(final int N, final int K) {
        int exclude = 0;
        int factN = fact(N);
        for (int i = 1; i <= 2 * N; i++) {
            int curr = i - K >= 1 ? K : (i - 1);
            curr += i + K <= 2 * N ? K : (2 * N - i);
            curr = 2 * N - curr - 1;
            for (int j = 2 * (N - 1); j >= 1; j--) {
                curr = (curr * j) % MOD;
            }

            int[] gcd = gcd(curr, factN);

            curr = (curr * gcd[2]) % MOD;

            exclude = (exclude + curr) % MOD;
        }

        int fact2N = fact(2 * N);

        int[] gcd = gcd(fact2N, factN);

        fact2N = (fact2N * gcd[2]) % MOD;

        int res = (fact2N - exclude) % MOD;
        while (res < 0) {
            res = (res + MOD) % MOD;
        }

        return res;

    }

    public static void main(final String[] args) {
        AlienAndSetDiv2 a = new AlienAndSetDiv2();

        System.out.println(a.getNumber(2, 1));
    }
}
