import java.util.LinkedList;
import java.util.Queue;

/**
 * @author  tasyrkin
 * @since   2014/03/29
 */
public class TorusSailingEasy {

    private static class P {
        int x;
        int y;
        int d = 0;

        public P(final int ix, final int iy, final int id) {
            x = ix;
            y = iy;
            d = id;
        }

    }

    public double expectedTime(final int N, final int M, final int goalX, final int goalY) {

        int x = 0;
        int y = 0;
        boolean flag = false;
        for (int i = 0; i < N * M; i++) {
            if (x == goalX && y == goalY) {
                flag = true;
                break;
            }

            x = step(x + 1, N);
            y = step(y + 1, M);

        }

        if (!flag) {
            return -1;
        }

        P p = new P(0, 0, 0);
        Queue<P> q = new LinkedList<P>();
        q.offer(p);

        double r = 0.0;
        int c = 1000000;
        while (!q.isEmpty() && c-- > 0) {
            P e = q.poll();
            if (e.x == goalX && e.y == goalY) {
                r += e.d * (1d / Math.pow(2, e.d));
                q.offer(new P(step(e.x - 1, N), step(e.y - 1, M), e.d + 1));
                q.offer(new P(step(e.x + 1, N), step(e.y + 1, M), e.d + 1));
            }

        }

        return r;
    }

    private int step(final int e, final int V) {
        return (e + V) % V;
    }

    public static void main(final String[] args) {
        double r = new TorusSailingEasy().expectedTime(2, 2, 1, 1);
        System.out.println(r);
    }
}
