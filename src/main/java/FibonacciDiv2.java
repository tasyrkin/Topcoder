/**
 * @author  tasyrkin
 * @since   2014/05/29
 */
public class FibonacciDiv2 {
    public int find(final int N) {
        int[] a = new int[1000000];
        a[0] = 0;
        a[1] = 1;
        for (int i = 2; i < a.length; i++) {
            a[i] = a[i - 1] + a[i - 2];
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (Math.abs(N - a[i]) < min) {
                min = Math.abs(N - a[i]);
            }
        }

        return min;
    }

    public static void main(final String[] args) {
        FibonacciDiv2 s = new FibonacciDiv2();

        System.out.println(s.find(1000000));
    }

}
