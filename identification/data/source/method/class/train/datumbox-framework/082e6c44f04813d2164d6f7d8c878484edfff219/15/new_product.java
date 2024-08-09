public static double negativeBinomialCdf(int n, int r, double p) {
        if(n<0 || r<0 || p<0) {
            throw new IllegalArgumentException("All the parameters must be positive.");
        }
        n = Math.max(n,r);
        
        double probabilitySum = 0.0;
        for(int i=0;i<=r;++i) {
            probabilitySum += negativeBinomial(n, i, p);
        }
        
        return probabilitySum;
    }