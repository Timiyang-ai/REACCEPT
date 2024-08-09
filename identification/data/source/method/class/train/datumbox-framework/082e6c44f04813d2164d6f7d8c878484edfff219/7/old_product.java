public static double Geometric(int k, double p) {
        if(k<=0 || p<0) {
            throw new IllegalArgumentException("All the parameters must be positive.");
        }
        
        double probability = Math.pow(1-p,k-1)*p;
        
        return probability;
    }