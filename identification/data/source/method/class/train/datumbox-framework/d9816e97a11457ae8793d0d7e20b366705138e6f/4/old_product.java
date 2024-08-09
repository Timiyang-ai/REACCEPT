public static double NegativeBinomialCdf(int n, int r, double p) throws IllegalArgumentException {
        if(n<0 || r<0 || p<0) {
            throw new IllegalArgumentException();
        }
        n = Math.max(n,r);
        
        double probabilitySum = 0.0;
        for(int i=0;i<=r;++i) {
            probabilitySum += NegativeBinomial(n, i, p);
        }
        
        return probabilitySum;
    }