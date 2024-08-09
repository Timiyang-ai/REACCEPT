public static double GeometricCdf(int k, double p) {
        if(k<=0 || p<0) {
            throw new IllegalArgumentException("All the parameters must be positive.");
        }
        
        double probabilitySum = 0.0;
        for(int i=1;i<=k;++i) {
            probabilitySum += Geometric(i, p);
        }
        
        return probabilitySum;
    }