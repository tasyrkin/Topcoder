import java.math.BigInteger;

public class FoxAndGCDLCM {
	public long get(long G, long L) {
		if (L % G != 0)
			return -1;
		long coeff = L / G;
		long res = Long.MAX_VALUE;
		for (long i = 1; i * i <= coeff; i++) {
			if (coeff % i == 0) {
				long j = coeff / i;
				if (L == i * j * G && LCM(i * G, j * G) == L) {
					if (res > (i + j) * G){
						res = (i + j) * G;
					}
				}
			}
		}
		return res;
	}

	public long GCD(long a, long b) {
		if (a < b)
			return GCD(b, a);
		if (b == 0)
			return a;
		return GCD(b, a % b);
	}

	public long LCM(long a, long b) {
		BigInteger a1 = new BigInteger(a + "");
		BigInteger b1 = new BigInteger(b + "");
		BigInteger gcd = new BigInteger(GCD(a, b) + "");
		return a1.multiply(b1).divide(gcd).longValue();
	}

	public static void main(String[] args) {
		FoxAndGCDLCM f = new FoxAndGCDLCM();
		long res = f.get(999999999989l, 999999999989l);
		System.out.println(res);
	}
}
