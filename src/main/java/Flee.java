public class Flee {
    public double maximalSafetyLevel(int[] x, int[] y){
        if(x.length == 2){
            return Math.min(dist(x[0], y[0]), dist(x[1], y[1]));
        }
        if(x.length == 1){
            return dist(x[0], y[0]);
        }
        int cnt = 0;
        for(int i = 0; i < x.length; i++){
          for(int j = i+1; j < x.length; j++){
            if(x[i] != x[j] || y[i] != y[j]){
              cnt++;
            }
          }
        }
        if(cnt < 3){
          return Math.min(dist(x[0], y[0]), Math.min(dist(x[1], y[1]), dist(x[2], y[2])));
        }


        if(!inTriangle(x[0], y[0], x[1], y[1], x[2], y[2])){
            return Math.min(Math.min(dist(x[0], y[0]), dist(x[1], y[1])), dist(x[2], y[2]));
        }

        double d12 = dist(x[1]-x[0], y[1] - y[0]) / 2;
        double d13 = dist(x[2]-x[0], y[2] - y[0]) / 2;
        double d23 = dist(x[1]-x[2], y[1] - y[2]) / 2;

        double start = Math.min(Math.min(dist(x[0], y[0]), dist(x[1], y[1])), dist(x[2], y[2]));

        return Math.min(start, Math.max(d12, Math.max(d13, d23)));
    }

    double dist (int x, int y){
        return Math.sqrt(x*x + y*y);
    }

    double s (int x1, int y1, int x2, int y2, int x3, int y3)
    {
        return (x1 - x3) * (y2 - y3) - (x2 - x3) * (y1 - y3);
    }

    boolean inTriangle(int x1, int y1, int x2, int y2, int x3, int y3)
    {
        boolean b1, b2, b3;

        b1 = s(0, 0, x1, y1, x2, y2) < 0.0;
        b2 = s(0, 0, x2, y2, x3, y3) < 0.0;
        b3 = s(0, 0, x3, y3, x1, y1) < 0.0;

        return ((b1 == b2) && (b2 == b3));
    }
}
