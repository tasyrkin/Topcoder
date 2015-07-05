/**
 * @author  tasyrkin
 * @since   2014/04/26
 */
public class Unique {
    public String removeDuplicates(final String S) {
        int[] s = new int[1000];

        StringBuilder sb = new StringBuilder();
        for (char ch : S.toCharArray()) {
            if (s[ch] == 0) {
                sb.append(ch);
                s[ch] = 1;
            }
        }

        return sb.toString();

    }

    public static void main(String[] args) {
        System.out.println(new Unique().removeDuplicates("aabbc"));
    }
}
