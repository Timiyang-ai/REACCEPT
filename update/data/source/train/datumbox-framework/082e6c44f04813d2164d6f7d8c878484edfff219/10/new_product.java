public static double bernoulli(boolean k, double p) {
        if(p<0) {
            throw new IllegalArgumentException("The probability p can't be negative.");
        }
        
        return (k)?p:(1-p);
    }