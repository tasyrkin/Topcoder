import java.util.Arrays;

/**
 * @author  tasyrkin
 * @since   2014/01/30
 */
public class EllysSubstringSorter {
    public String getMin(final String S, final int L) {
        char[] ch = S.toCharArray();
        String[] arr = new String[ch.length - L];
        for (int i = 0; i < ch.length - L; i++) {
            char[] curr = new String(ch, i, L).toCharArray();
            Arrays.sort(curr);
            arr[i] = S.substring(0, i) + new String(curr) + S.substring(i + L);
        }

        Arrays.sort(arr);
        return arr[0];
    }
}
