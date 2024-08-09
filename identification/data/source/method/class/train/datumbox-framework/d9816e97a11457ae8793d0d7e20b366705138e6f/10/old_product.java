public static double NegativeBinomial(int n, int r, double p) throws IllegalArgumentException {
        //tested its validity with http://www.mathcelebrity.com/binomialneg.php
        if(n<0 || r<0 || p<0) {
            throw new IllegalArgumentException();
        }
        n = Math.max(n,r);//obvisouly the total number of tries must be larger than the number of required successes
        
        double probability = ArithmeticMath.combination(n-1, r-1)*Math.pow(1-p,n-r)*Math.pow(p,r);
        
        return probability;
    }