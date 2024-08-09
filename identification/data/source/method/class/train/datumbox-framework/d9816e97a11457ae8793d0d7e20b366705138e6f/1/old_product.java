public static double Bernoulli(boolean k, double p) throws IllegalArgumentException {
        if(p<0) {
            throw new IllegalArgumentException();
        }
        
        return (k)?p:(1-p);
    }