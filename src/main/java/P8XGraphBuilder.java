
public class P8XGraphBuilder {
	static int[][][] dp = null;
	int min = -1;
	int max = -1;
	int sum = -1;
	int ans = Integer.MIN_VALUE;

	public int solve(int[] scores) {
//		int n = scores.length + 1;
//		if (n == 2)
//			return 2 * scores[0];
//		int[] arr = new int[n];
//		solve(arr, 0, 1, 0, scores);
//		return ans;
		int n = scores.length + 1;
		if (n == 2)
			return 2 * scores[0];
		sum = n + n - 2;
		min = 1;
		max = n - 1;
		int[][]dp = new int[n][n];
		int res = Integer.MIN_VALUE;
		for(int edge = 1; edge < n; edge++){
		}
		return 0;
	}

	public void solve(int[] arr, int step, int prevScore, int currSum, int[] scores) {
		if (currSum > sum)
			return;
		if (step == arr.length) {
			if (sum == currSum) {
				int curr = 0;
				for (int i = 0; i < arr.length; i++) {
					curr += scores[arr[i] - 1];
				}
				if (curr > ans)
					ans = curr;
			}
			return;
		}
		if (step == arr.length - 1) {
			if (sum - currSum >= 1) {
				arr[step] = sum - currSum;
				solve(arr, step + 1, arr[step], currSum + arr[step], scores);
			}
		}
		for (int i = prevScore; i <= max; i++) {
			arr[step] = i;
			solve(arr, step + 1, i, currSum + i, scores);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		P8XGraphBuilder o = new P8XGraphBuilder();
		 int res = o.solve(new int[] { 5, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 2, 1,
		 2, 3, 4, 5, 6, 2, 3,
		 4, 6, 7, 5, 4, 3, 45, 6, 7, 8, 6, 5, 4, 3, 4, 5, 6, 7, 8, 7, 6, 5, 5,
		 4, 4, 3, 3,
		 4, 5, 6 });
//		int res = o.solve(new int[] { 5, 0, 0 });
		System.out.println(res);
	}
}
