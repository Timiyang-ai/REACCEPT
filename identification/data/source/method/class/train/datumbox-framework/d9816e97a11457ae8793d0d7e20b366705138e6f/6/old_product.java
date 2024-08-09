public static double Uniform(int n) throws IllegalArgumentException {
        if(n<1) {
            throw new IllegalArgumentException();
        }
        
        double probability = 1.0/n;
        
        return probability;
    }