public static double HypergeometricCdf(int k, int n, int Kp, int Np) throws IllegalArgumentException {
        if(k<0 || n<0 || Kp<0 || Np<0) {
            throw new IllegalArgumentException();
        }
        Kp = Math.max(k, Kp);
        Np = Math.max(n, Np);
        
        /*
        //slow!
        $probabilitySum=0;
        for($i=0;$i<=$k;++$i) {
            $probabilitySum+=self::Hypergeometric($i,$n,$Kp,$Np);
        }
        */

        //fast and can handle large numbers
        //Cdf(k)-Cdf(k-1)
        double probabilitySum = approxHypergeometricCdf(k,n,Kp,Np);
        
        return probabilitySum;
    }