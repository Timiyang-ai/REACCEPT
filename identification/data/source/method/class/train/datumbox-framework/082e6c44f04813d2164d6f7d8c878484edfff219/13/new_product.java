public static double uniform(int n) {
        if(n<1) {
            throw new IllegalArgumentException("The n must be larger than 1.");
        }
        
        double probability = 1.0/n;
        
        return probability;
    }