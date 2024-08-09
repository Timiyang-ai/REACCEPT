public static double BinomialCdf(int k, double p, int n) {
        if(k<0 ||  p<0 || n<1) {
            throw new IllegalArgumentException("All the parameters must be positive and n larger than 1.");
        }
        
        k = Math.min(k, n);
        
        double probabilitySum=0.0;
        
        /*
        //slow!
        for($i=0;$i<=$k;++$i) {
            $probabilitySum+=self::Binomial($i,$p,$n);
        }
        */

        probabilitySum = approxBinomialCdf(k,p,n); 
        
        return probabilitySum;
    }