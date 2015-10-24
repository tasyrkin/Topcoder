import java.util.HashSet;
import java.util.Set;

public class SubstitutionCipher {
    public String decode(String a, String b, String y){
        Character[]table = new Character[26];

        Set<Character> missing = new HashSet<>();
        for(char i = 'A'; i <= 'Z'; i++){
            missing.add(i);
        }

        char[] ach = a.toCharArray();

        int idx = 0;
        Set<Integer> fixed = new HashSet<>();
        for(int ch : b.toCharArray()){
            int currIdx = ch - 'A';

            table[currIdx] = ach[idx];
            fixed.add(currIdx);

            missing.remove(ach[idx]);

            idx++;
        }

        int countNulls = 0;
        for(int ch : y.toCharArray()){
            if(table[ch-'A'] == null){
                countNulls++;
            }
        }

        StringBuilder sb = new StringBuilder();
        if(countNulls == 0){
            for(int ch : y.toCharArray()){
                sb.append(table[ch-'A']);
            }
            return sb.toString();
        } else if(missing.size() == 1){
            for(int ch : y.toCharArray()){
                Character mych = table[ch-'A'];
                if(mych == null){
                    sb.append(missing.iterator().next());
                } else{
                    sb.append(mych);
                }

            }
            return sb.toString();
        }
        return "";

/*

        if(fixed.size() == table.length || fixed.size() == table.length - 1){
            StringBuilder sb = new StringBuilder();
            if(fixed.size() == table.length){
                for(int ch : y.toCharArray()){
                    sb.append(table[ch-'A']);
                }
                return sb.toString();
            } else {
                for(int ch : y.toCharArray()){
                    Character mych = table[ch-'A'];
                    if(mych == null){
                        sb.append(missing.iterator().next());
                    } else{
                        sb.append(mych);
                    }

                }
            }
        } else {
            return "";
        }
        return "";
*/
    }

    public static void main(String[] args) {
        String res = new SubstitutionCipher().decode("CAT", "DOG", "GOD");
        System.out.println(res);
    }
}
