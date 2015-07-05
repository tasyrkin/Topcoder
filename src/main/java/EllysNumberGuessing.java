/**
 * @author  tasyrkin
 * @since   2014/01/30
 */
public class EllysNumberGuessing {
    public int getNumber(final int[] g, final int[] a) {
        long a1 = (long) g[0] - (long) a[0];
        long a2 = (long) g[0] + (long) a[0];

        if (a1 < 1 || a1 > 1000000000) {
            a1 = Integer.MIN_VALUE;
        }

        if (a2 < 1 || a2 > 1000000000) {
            a2 = Integer.MIN_VALUE;
        }

        // System.out.println(a1 + " " + a2);

        int cnt1 = a1 > 0 ? 1 : 0;
        int cnt2 = a2 > 0 ? 1 : 0;
        for (int i = 1; i < g.length; i++) {
            long c1 = (long) g[i] - (long) a[i];
            long c2 = (long) g[i] + (long) a[i];
            if (c1 == a1 || c2 == a1) {
                cnt1++;
            }

            if (c1 == a2 || c2 == a2) {
                cnt2++;
            }

            // System.out.println(c1 + " " + c2);
        }

        if (cnt1 == g.length && cnt2 == g.length) {
            return -1;
        }

        if (cnt1 == g.length) {
            return (int) a1;
        }

        if (cnt2 == g.length) {
            return (int) a2;
        }

        return -2;
    }
}
