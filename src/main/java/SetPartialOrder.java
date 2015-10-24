import java.util.HashSet;
import java.util.Set;

public class SetPartialOrder {
    public String compareSets(int[] a, int[] b){
        Set<Integer> sa = new HashSet<>();
        Set<Integer> sb = new HashSet<>();
        for(int xa : a){
            sa.add(xa);
        }
        for(int xb : b){
            sb.add(xb);
        }

        if(sa.size() == sb.size()){
            for(int xa : sa){
                sb.remove(xa);
            }
            if(sb.size() == 0){
                return "EQUAL";
            } else {
                return "INCOMPARABLE";
            }
        } else if(sa.size() < sb.size()){
            for(int xa : sa){
                sb.remove(xa);
            }
            if(sb.size() == b.length - a.length){
                return "LESS";
            } else {
                return "INCOMPARABLE";
            }
        } else if (sa.size() > sb.size()){
            for(int xb : sb){
                sa.remove(xb);
            }
            if(sa.size() == a.length - b.length){
                return "GREATER";
            } else {
                return "INCOMPARABLE";
            }
        }
        return "";
    }
}
