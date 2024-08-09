public static double hypergeometric(int k, int n, int Kp, int Np) {
        if(k<0 || n<0 || Kp<0 || Np<0) {
            throw new IllegalArgumentException("All the parameters must be positive.");
        }
        Kp = Math.max(k, Kp);
        Np = Math.max(n, Np);
        
        /*
        //slow!
        $probability=StatsUtilities::combination($Kp,$k)*StatsUtilities::combination($Np-$Kp,$n-$k)/StatsUtilities::combination($Np,$n);
        */

        //fast and can handle large numbers
        //Cdf(k)-Cdf(k-1)
        double probability = approxHypergeometricCdf(k,n,Kp,Np);
        if(k>0) {
            probability -= approxHypergeometricCdf(k-1,n,Kp,Np);
        }

        
        return probability;
    }