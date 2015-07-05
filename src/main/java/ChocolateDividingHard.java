import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class ChocolateDividingHard {

    private static class State {
        int[] y = new int[3];
        int[] x = new int[3];

        public State(final int[] x, final int[] y) {
            for (int i = 0; i < x.length; i++) {
                this.x[i] = x[i];
                this.y[i] = y[i];
            }
        }

        public State(final int x1, final int x2, final int x3, final int y1, final int y2, final int y3) {
            x[0] = x1;
            x[1] = x2;
            x[2] = x3;
            y[0] = y1;
            y[1] = y2;
            y[2] = y3;
        }

        public State addX(final int idx, final int val) {
            int[] newX = new int[3];
            for (int i = 0; i < x.length; i++) {
                newX[i] = x[i];
            }

            newX[idx] += val;

            return new State(newX, y);
        }

        public State addY(final int idx, final int val) {
            int[] newY = new int[3];
            for (int i = 0; i < x.length; i++) {
                newY[i] = y[i];
            }

            newY[idx] += val;

            return new State(x, newY);
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            State state = (State) o;

            if (!Arrays.equals(x, state.x)) {
                return false;
            }

            if (!Arrays.equals(y, state.y)) {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            int result = Arrays.hashCode(y);
            result = 31 * result + Arrays.hashCode(x);
            return result;
        }

        @Override
        public String toString() {
            return "State{" + "x=" + Arrays.toString(x) + ", y=" + Arrays.toString(y) + '}';
        }
    }

    public int findBest(final String[] c) {
        int[][] sums = new int[c.length][c[0].length()];
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[0].length(); j++) {
                sums[i][j] = Integer.parseInt("" + c[i].charAt(j)) + (i - 1 >= 0 ? sums[i - 1][j] : 0)
                        + (j - 1 >= 0 ? sums[i][j - 1] : 0) - ((i - 1 >= 0 && j - 1 >= 0) ? sums[i - 1][j - 1] : 0);
            }
        }

        Queue<State> q = new LinkedList<State>();
        q.add(new State(0, 1, 2, 0, 1, 2));

        Set<State> visited = new HashSet<State>();
        int maxMin = 0;
        while (!q.isEmpty()) {
            State currState = q.remove();
            if (visited.contains(currState)) {
                continue;
            }

            visited.add(currState);

            int currMaxMin = calculate(sums, currState, c[0].length(), c.length);
            if (currMaxMin > maxMin) {
                maxMin = currMaxMin;
            }

            for (int i = 0; i < 3; i++) {
                for (int diff = -1; diff <= 1; diff++) {
                    if (diff == 0) {
                        continue;
                    }

                    if (isValid(currState.x, i, currState.x[i] + diff, c[0].length())) {
                        State next = currState.addX(i, diff);
                        int nextVal = calculate(sums, next, c[0].length(), c.length);
                        if (nextVal >= currMaxMin) {
                            q.add(next);
                        }
                    }

                    if (isValid(currState.y, i, currState.y[i] + diff, c.length)) {
                        State next = currState.addY(i, diff);
                        int nextVal = calculate(sums, next, c[0].length(), c.length);
                        if (nextVal >= currMaxMin) {
                            q.add(next);
                        }
                    }
                }
            }
        }

        return maxMin;
    }

    private static boolean isValid(final int[] arr, final int idx, final int val, final int len) {
        //J-
        return val >= 0
            && val < len - 1
            && (val != arr[(idx + 1) % 3])
            && (val != arr[(idx + 2) % 3]);
        //J+
    }

    public static int calculate(final int[][] sums, final State s, final int xlen, final int ylen) {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i <= 3; i++) {
            int prevIdxX = i == 0 ? 0 : s.x[i - 1];
            int idxX = i < 3 ? s.x[i] : xlen - 1;
            for (int j = 0; j <= 3; j++) {
                int prevIdxY = j == 0 ? 0 : s.y[j - 1];
                int idxY = j < 3 ? s.y[j] : ylen - 1;
                int curr = 0;
                if (i == 0 && j == 0) {
                    curr = sums[idxY][idxX];
                } else if (i == 0) {
                    curr = sums[idxY][idxX] - sums[prevIdxY][idxX];
                } else if (j == 0) {
                    curr = sums[idxY][idxX] - sums[idxY][prevIdxX];
                } else {
                    curr = sums[idxY][idxX] + sums[prevIdxY][prevIdxX] - sums[idxY][prevIdxX] - sums[prevIdxY][idxX];
                }

                if (curr < res) {
                    res = curr;
                }
            }
        }

        return res;
    }

    public static void main(final String[] args) {
        ChocolateDividingHard cdh = new ChocolateDividingHard();

        //J-
        int v = cdh.findBest(new String[] {
                "95998",
                "21945",
                "23451",
                "99798",
                "74083"});
        //J+
        System.out.println(v);
    }
}
