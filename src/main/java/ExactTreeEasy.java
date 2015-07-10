public class ExactTreeEasy {
    public int[] getTree(int n, int m){
        int[] res = new int[2*(n-1)];
        int idx = 0;
        for(int i = 0; i < m; i++){
            res[idx] = 0;
            res[idx+1] = i+1;
            idx+=2;
        }
        for(int i = m; i < n-1; i++){
            res[idx] = i;
            res[idx+1] = i+1;
            idx+=2;
        }
        return res;
    }
}
