public class AB {
    public String createString(int N, int K) {
        if(N <= 1) throw new IllegalArgumentException("N must be >= 2");
        if(K < 0) throw new IllegalArgumentException("K must be >= 0");
        int As = N/2 + (N%2 == 1 ? 1 : 0);
        int Bs = N/2;
        int maxPairs = As * Bs;
        if(K > maxPairs) return "";

        final char[] result = init(N, As);
        int remainingDiff = swap(result, K, As, Bs);
        shift(result, K, remainingDiff);

        return new String(result);
    }

    private void shift(char[] result, int K, int diff) {
        int lastA = -1;
        for(int idx = 0; idx < result.length; idx++) {
            if(result[idx] == 'B') {
                lastA = idx - 1;
                break;
            }
        }
        if(lastA >= 0) {
            for (int idx = lastA; diff > 0; idx++) {
                result[idx] = 'B';
                result[idx+1] = 'A';
                diff--;
            }
        }
    }

    private int swap(char[] result, int K, int As, int Bs) {
        int currPairs = As * Bs;
        int diff = currPairs - K;

        int lastA = As-1;
        int lastB = result.length-1;
        while (diff >= Bs) {
            result[lastA] = 'B';
            result[lastB] = 'A';
            lastA--;
            lastB--;
            currPairs -= Bs;
            diff = currPairs - K;
        }
        return diff;
    }

    private char[] init(int N, int As) {
        char[] result = new char[N];
        for(int idx = 0; idx < As; idx++) {
            result[idx] = 'A';
        }
        for(int idx = As; idx < N; idx++) {
            result[idx] = 'B';
        }
        return result;
    }

    public static void main(String[] args) {
        AB ab = new AB();

//        System.out.println(ab.createString(4, 4));
//        System.out.println(ab.createString(4, 5));
//        System.out.println(ab.createString(15, 33));
//        System.out.println(ab.createString(15, 0));
        System.out.println(ab.createString(3, 2));
        System.out.println(ab.createString(2, 0));
        System.out.println(ab.createString(2, 1));
        System.out.println(ab.createString(5, 8));
        System.out.println(ab.createString(50, 1));
    }
}