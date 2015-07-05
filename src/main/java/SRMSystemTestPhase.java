
public class SRMSystemTestPhase {
	public static final int MOD = 1000000007;

	public static int f(int n) {
		if (n <= 1)
			return 1;
		else if (n == 2)
			return 2;
		else if (n == 3)
			return 6;
		return 1;
	}

	public static int cnk(int n, int k) {
		if (k <= 0 || n <= 0)
			return 1;
		return f(n) / f(k) / f(n - k);
	}

	public int countWays(String[] description) {
		int[][][] dp = new int[51][4][4];
		int[] subm = new int[description.length];
		for (int i = 0; i < description.length; i++) {
			int submitted = 0;
			if (description[i].charAt(0) == 'Y')
				submitted++;
			if (description[i].charAt(1) == 'Y')
				submitted++;
			if (description[i].charAt(2) == 'Y')
				submitted++;
			subm[i] = submitted;
		}
		for (int k = 0; k < description.length; k++) {
			for (int i = 0; i <= subm[k]; i++) {
				for (int j = 0; j <= subm[k]; j++) {
					if (i + j > subm[k])
						continue;
					int currDp = cnk(subm[k], i) * cnk(subm[k] - i, j)
							* cnk(subm[k] - i - j, subm[k] - i - j);
					if (k != 0) {
						int sum = 0;
						for (int i1 = 0; i1 <= subm[k - 1]; i1++) {
							for (int j1 = 0; j1 <= subm[k - 1]; j1++) {
								if (i < i1 || (i == i1 && j >= j1)) {
									long currRes = dp[k - 1][i1][j1] % MOD;
									currRes = (currRes * (long) currDp);
									currRes %= MOD;
									sum = ((sum % MOD) + ((int) currRes % MOD)) % MOD;
								}
							}
						}
						dp[k][i][j] = sum;
					} else {
						dp[k][i][j] = currDp;
					}
				}
			}
		}
		int res = 0;
		for (int i = 0; i <= subm[description.length - 1]; i++) {
			for (int j = 0; j <= subm[description.length - 1]; j++) {
				res = ((res % MOD) + (dp[description.length - 1][i][j] % MOD)) % MOD;
			}
		}
		return res;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SRMSystemTestPhase tph = new SRMSystemTestPhase();
		String[] description = { "YYY" };
		int res = tph.countWays(description);
		System.out.println(res);
	}

}
