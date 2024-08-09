public static double combination(int n, int k) {
        if(n<k) {
            throw new IllegalArgumentException("The n can't be smaller than k.");
        }
        return factorial(n)/(factorial(k)*factorial(n-k));
    }