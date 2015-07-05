import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: tasyrkin
 * @since: 2014/06/12
 */
public class BuildingHeightsEasy {
    
    public int minimum(int M, int[] h){
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        for(int i : h){
            Integer fl = m.get(i);
            if(fl == null)fl = 0;
            fl++;
            m.put(i, fl);
        }
        Integer[] fls = m.keySet().toArray(new Integer[0]);
        Arrays.sort(fls);
        
        Integer minAdd = Integer.MAX_VALUE;
        for(int floorIdx = 1; floorIdx < fls.length; floorIdx++){
            Integer exists = m.get(fls[floorIdx]);
            if(exists >= M){
                minAdd = 0;
                break;
            }
            int currAdd = 0;
            boolean enough = false;
            for(int j = floorIdx-1; j >= 0; j--){
                if(exists+m.get(fls[j])>=M){
                    currAdd += (fls[floorIdx] - fls[j]) * (M - exists);
                    enough = true;
                    break;
                } else {
                    currAdd += (fls[floorIdx] - fls[j]) * m.get(fls[j]);
                    exists+=m.get(fls[j]);
                }
            }
            if(currAdd < minAdd && enough){
                minAdd = currAdd;
            }
        }
        
        return minAdd;
    }

    public static void main(String[] args) {
        BuildingHeightsEasy b = new BuildingHeightsEasy();
        System.out.println(b.minimum(3, new int[]{19, 23, 9, 12}));
        
        
    }
}
