public static double chisquareCdf(double x, int df) {
        if(df<=0) {
            throw new IllegalArgumentException("The degrees of freedom need to be positive.");
        }
        
        return ContinuousDistributions.gammaCdf(x/2.0, df/2.0);
    }