public static double Poisson(int k, double lamda) {
        if(k<0 || lamda<0) {
            throw new IllegalArgumentException("All the parameters must be positive.");
        }
        
        /*
        //Slow
        $probability=pow($lamda,$k)*exp(-$lamda)/StatsUtilities::factorial($k);
        */

        //fast and can handle large numbers
        //Cdf(k)-Cdf(k-1)
        double probability = PoissonCdf(k,lamda);
        if(k>0) {
            probability -= PoissonCdf(k-1,lamda);
        }
        
        return probability;
    }