
public class BearCheats {
    public String eyesight(int A, int B){
        char[] as = (""+A).toCharArray();
        char[] bs = (""+B).toCharArray();

        int changes = 0;
        for(int i = 0;i < as.length; i++){
            if(as[i] != bs[i])changes++;
        }
        return changes <= 1 ? "happy" : "glasses";
    }
}
