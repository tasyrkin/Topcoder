import java.util.*;

public class PrivateD2party {
    public int getsz(int[] a){
        Set[]gr = new Set[a.length];
        for(int i = 0; i < a.length; i++){
            if(gr[a[i]] == null){
                gr[a[i]] = new HashSet();
            }
            if(i != a[i])gr[a[i]].add(i);
        }
        Set<Integer> used = new HashSet<>();
        int invited = 0;
        while(used.size() < a.length){
            Map<Integer, List<Integer>> m = new HashMap<>();
            for(int i = 0; i < a.length; i++){
                if(used.contains(i))continue;
                int deg = gr[i] == null ? 0 : gr[i].size();
                List<Integer> l = m.get(deg);
                if(l == null)l = new ArrayList<>();
                l.add(i);
                m.put(deg, l);
            }
            Integer[] degs = m.keySet().toArray(new Integer[0]);
            Arrays.sort(degs);
            List<Integer> l = m.get(degs[0]);
            int idx = l.iterator().next();
            invited++;
            used.add(idx);
            if(a[idx] != idx){
                gr[a[idx]].remove(idx);
                if(gr[idx] != null){
                    for(Object bad : gr[idx]){
                        used.add((Integer)bad);
                    }
                }
            }
        }

        return invited;
    }
}