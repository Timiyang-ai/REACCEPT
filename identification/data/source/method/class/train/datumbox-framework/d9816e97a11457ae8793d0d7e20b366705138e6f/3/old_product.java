public static double UniformCdf(int k, int n) throws IllegalArgumentException {
        if(k<0 || n<1) {
            throw new IllegalArgumentException();
        }
        k = Math.min(k, n);
        
        double probabilitySum = k*Uniform(n);
        
        return probabilitySum;
    }