import java.util.Arrays;

public class ColorfulRoad {

    private static char[] JUMP_RULES = "RGB".toCharArray();

    public static void main(String[] args) {
        System.out.println("RGGGB:" + new ColorfulRoad().getMin("RGGGB"));
        System.out.println("RGBRGBRGB:" + new ColorfulRoad().getMin("RGBRGBRGB"));
        System.out.println("RBBGGGRR:" + new ColorfulRoad().getMin("RBBGGGRR"));
        System.out.println("RBRRBGGGBBBBR:" + new ColorfulRoad().getMin("RBRRBGGGBBBBR"));
        System.out.println("RG:" + new ColorfulRoad().getMin("RG"));
        System.out.println("RBRGBGBGGBGRGGG:" + new ColorfulRoad().getMin("RBRGBGBGGBGRGGG"));
    }

    public int getMin(String road) {
        char[] roadCh = road.toCharArray();

        int[] DP = new int[roadCh.length];
        Arrays.fill(DP, -2);

        return solve(roadCh, roadCh.length - 1, DP);
    }

    private int solve(final char[] road, final int index, final int[] DP) {
        if (index <= 0) return 0;

        if (DP[index] != -2) return DP[index];

        int minCost = Integer.MAX_VALUE;
        for (int currIdx = index - 1; currIdx >= 0; currIdx--) {
            if (canJump(road, index, currIdx)) {
                int solvedCost = solve(road, currIdx, DP);
                if (solvedCost != -1) {
                    int currCost = (index - currIdx) * (index - currIdx) + solvedCost;
                    if (minCost > currCost) minCost = currCost;
                }
            }
        }

        if (minCost == Integer.MAX_VALUE) {
            return -1;
        }

        DP[index] = minCost;

        return minCost;
    }

    private boolean canJump(final char[] road, final int to, final int from) {
        int index;
        for (index = 0; index < JUMP_RULES.length; index++) {
            if (JUMP_RULES[index] == road[from]) break;
        }
        return index != JUMP_RULES.length && JUMP_RULES[(index + 1) % JUMP_RULES.length] == road[to];
    }
}
