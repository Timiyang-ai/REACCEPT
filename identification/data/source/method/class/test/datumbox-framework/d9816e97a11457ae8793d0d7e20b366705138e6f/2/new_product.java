public static double PoissonCdf(int k, double lamda) {
        if(k<0 || lamda<0) {
            throw new IllegalArgumentException("All the parameters must be positive.");
        }
        
        /*
        //Slow!
        $probabilitySum=0;
        for($i=0;$i<=$k;++$i) {
            $probabilitySum+=self::Poisson($i,$lamda);
        }
        */

        //Faster solution as described at: http://www.math.ucla.edu/~tom/distributions/poisson.html?
        double probabilitySum = 1.0 - ContinuousDistributions.GammaCdf(lamda,k+1);
        
        return probabilitySum;
    }