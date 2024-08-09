public static double Geometric(int k, double p) throws IllegalArgumentException {
        if(k<=0 || p<0) {
            throw new IllegalArgumentException();
        }
        
        double probability = Math.pow(1-p,k-1)*p;
        
        return probability;
    }