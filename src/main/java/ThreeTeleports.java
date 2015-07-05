
public class ThreeTeleports {
	public int shortestDistance(int xMe, int yMe, int xHome, int yHome,
			String[] teleports) {
		long res = Math.abs(xHome - xMe) + Math.abs(yHome - yMe);
		long[][] tel = new long[4][teleports.length];
		for (int i = 0; i < teleports.length; i++) {
			String[] parts = teleports[i].split("\\s+");
			tel[0][i] = Long.parseLong(parts[0]);
			tel[1][i] = Long.parseLong(parts[1]);
			tel[2][i] = Long.parseLong(parts[2]);
			tel[3][i] = Long.parseLong(parts[3]);
		}
		int[][] perm = new int[6][3];
		int cnt = 0;
		perm[cnt][0] = 0;
		perm[cnt][1] = 1;
		perm[cnt][2] = 2;
		cnt++;
		perm[cnt][0] = 0;
		perm[cnt][1] = 2;
		perm[cnt][2] = 1;
		cnt++;
		perm[cnt][0] = 1;
		perm[cnt][1] = 0;
		perm[cnt][2] = 2;
		cnt++;
		perm[cnt][0] = 1;
		perm[cnt][1] = 2;
		perm[cnt][2] = 0;
		cnt++;
		perm[cnt][0] = 2;
		perm[cnt][1] = 1;
		perm[cnt][2] = 0;
		cnt++;
		perm[cnt][0] = 2;
		perm[cnt][1] = 0;
		perm[cnt][2] = 1;
		for (cnt = 0; cnt < perm.length; cnt++) {
			for (int i = 1; i < 64; i++) {
				long xs = xMe;
				long ys = yMe;
				long curr = 0;
				for (int j = 0; j < 3; j++) {
					if (((i >> (2 * j)) & 1) == 1) {
						if (((i >> (2 * j + 1)) & 1) == 1) {
							long toTel = Math.abs(xs - tel[0][perm[cnt][j]])
									+ Math.abs(ys - tel[1][perm[cnt][j]]);
							xs = tel[2][perm[cnt][j]];
							ys = tel[3][perm[cnt][j]];
							curr += toTel + 10;
						} else {
							long toTel = Math.abs(xs - tel[2][perm[cnt][j]])
									+ Math.abs(ys - tel[3][perm[cnt][j]]);
							xs = tel[0][perm[cnt][j]];
							ys = tel[1][perm[cnt][j]];
							curr += toTel + 10;
						}
					}
				}
				long toHome = Math.abs(xs - xHome) + Math.abs(ys - yHome);
				curr += toHome;
				if (res > curr)
					res = curr;
			}
		}
		return (int) res;
	}

	public static void main(String[] args) {
		ThreeTeleports tt = new ThreeTeleports();
		String[] teleports = { "3 10 5200 4900", "12212 8699 9999 30011",
				"12200 8701 5203 4845" };
		int res = tt.shortestDistance(3, 7, 10000, 30000, teleports);
		System.out.println(res);
	}
}