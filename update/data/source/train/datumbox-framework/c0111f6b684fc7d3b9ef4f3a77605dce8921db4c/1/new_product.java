public static double combination(int n, int k) {
        if(n<k) {
            throw new IllegalArgumentException("The n can't be smaller than k.");
        }
        double combinations=1.0;
        double lowerBound = n-k;
        for(int i=n;i>lowerBound;i--) {
            combinations *= i/(i-lowerBound);
        }
        return combinations;
    }