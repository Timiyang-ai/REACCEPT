public static double Binomial(int k, double p, int n) throws IllegalArgumentException {
        if(k<0 ||  p<0 || n<1) {
            throw new IllegalArgumentException();
        }
        
        k = Math.min(k, n); 
        
        /*
        //Slow and can't handle large numbers
        $probability=StatsUtilities::combination($n,$k)*pow($p,$k)*pow(1-$p,$n-$k);
        */

        //fast and can handle large numbers
        //Cdf(k)-Cdf(k-1)
        double probability = approxBinomialCdf(k,p,n); 
        if(k>0) {
            probability -= approxBinomialCdf(k-1,p,n);
        }
        
        return probability;
    }