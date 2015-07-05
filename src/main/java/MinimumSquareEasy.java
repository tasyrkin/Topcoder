import java.util.Arrays;
import java.util.Comparator;

/**
 * @author  tasyrkin
 * @since   2014/03/29
 */
public class MinimumSquareEasy {
    private static class P {
        int x;
        int y;

        public P(final int ix, final int iy) {
            x = ix;
            y = iy;
        }

    }

    public static class XCmp implements Comparator<P> {

        @Override
        public int compare(final P o1, final P o2) {
            return Integer.valueOf(o1.x).compareTo(Integer.valueOf(o2.x));
        }
    }

    public static class YCmp implements Comparator<P> {

        @Override
        public int compare(final P o1, final P o2) {
            return Integer.valueOf(o1.y).compareTo(Integer.valueOf(o2.y));
        }
    }

    public long minArea(final int[] x, final int[] y) {
        int n = x.length;
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new P(x[i], y[i]);
        }

        Arrays.sort(ps, new XCmp());

        long minSize = Long.MAX_VALUE;
        for (int l = n - 2; l <= n; l++) {
            for (int i = 0; i + l <= n; i++) {
                int b = ps[i].x - 1;
                int e = ps[i + l - 1].x + 1;
                long miny = Long.MAX_VALUE;
                long maxy = Long.MIN_VALUE;
                for (int j = 0; j <= i + l - 1; j++) {
                    if (miny > ps[j].y) {
                        miny = ps[j].y;
                    }

                    if (maxy < ps[j].y) {
                        maxy = ps[j].y;
                    }
                }

                if (maxy - miny <= e - b - 2) {
                    if (minSize > e - b) {
                        minSize = e - b;
                    }
                }
            }
        }

        Arrays.sort(ps, new YCmp());

        for (int l = n - 2; l <= n; l++) {
            for (int i = 0; i + l <= n; i++) {
                int b = ps[i].y - 1;
                int e = ps[i + l - 1].y + 1;
                long miny = Long.MAX_VALUE;
                long maxy = Long.MIN_VALUE;
                for (int j = 0; j <= i + l - 1; j++) {
                    if (miny > ps[j].x) {
                        miny = ps[j].x;
                    }

                    if (maxy < ps[j].x) {
                        maxy = ps[j].x;
                    }
                }

                if (maxy - miny <= e - b - 2) {
                    if (minSize > e - b) {
                        minSize = e - b;
                    }
                }
            }
        }

        return minSize * minSize;
    }
}
