public static double BernoulliCdf(int k, double p) throws IllegalArgumentException {
        if(p<0) {
            throw new IllegalArgumentException();
        }
        
        double probabilitySum=0.0;
        if(k<0) {
            
        }
        else if(k<1) { //aka k==0
            probabilitySum=(1-p);
        }
        else { //k>=1 aka k==1
            probabilitySum=1.0;
        }
        
        return probabilitySum;
    }