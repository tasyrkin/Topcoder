import java.util.HashSet;
import java.util.Set;

/**
 * @author  tasyrkin
 * @since   2014/01/21
 */
public class AlienAndPassword {
    public int getNumber(final String S) {
        Set<String> set = new HashSet<String>();

        for (int i = 0; i < S.length(); i++) {
            set.add(S.substring(0, i) + S.substring(i + 1, S.length()));
        }

        return set.size();
    }
}
