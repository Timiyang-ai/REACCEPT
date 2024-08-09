public static double zeta(double x, double a)
    {
        if(x == 0)
            return 0.5-a;
        if(a <= 0 || x < 0)
            return Double.NaN;//Results would be complex infinity
        if(x == 1)
            return Double.NaN;//Result would be complex infinity
        if(a > 1e7)//In the limit this is correct. At 1e6, relative error is <= 10^(-8) for x [1, 10,000[
        {
            return (1/(x - 1) + 1/(2*a))*pow(a, 1 - x);
        }
        
        //The error on this seems high... so lets not do that for now
//        if(x <= -1 && Math.rint(x) == x && x >= Integer.MIN_VALUE)//Use https://dlmf.nist.gov/25.11.E13 
//        {
//            int n = (int) -x;
//            return -exp(reLnBn(n+1)+log(a)-log(n+1));
//        }
        
        //General case, done as outlined here https://math.stackexchange.com/questions/917100/numerical-evaluation-of-hurwitz-zeta-function?rq=1 
        
        double part1 = 0;
        double part2 = 0;
        final int n = 9;//Constant chosen by recomendation for double precision
        for(int k = 0; k <= n; k++)
        {
            part1 += 1/pow(a+k, x);
        }
        
        //Second summerant term is zetBernCoefs[k] * ( ((a+n)^(-k-x) Gamma[-2+3 k+x])/Gamma[-2+2 k+x] )
        //simplfy 2nd term with logs as exp( -(k+x) Log[a+n]+Log[Gamma[-2+3 k+x]]-Log[Gamma[-2+2 k+x]] )
        
        for(int k = 1; k < zetBernCoefs.length; k++ )
        {
            part2 += zetBernCoefs[k] * exp(-(k+x)* log(a+n)+lnGamma(-2+3*k+x)-lnGamma(-2+2*k+x));
        }
        
        return part1 + pow(a+n, 1-x)/(x-1) - 1./(2*pow(a+n, x)) + part2 ;
    }