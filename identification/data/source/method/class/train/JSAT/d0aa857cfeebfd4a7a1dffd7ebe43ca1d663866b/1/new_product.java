public static PairedReturn<Integer, Double> threshholdSplit(final ContinuousDistribution dist1, final ContinuousDistribution dist2)
    {
        if(dist1 == null && dist2 == null)
            throw new ArithmeticException("No Distributions given");
        else if(dist1 == null)
            return new PairedReturn<Integer, Double>(1, Double.POSITIVE_INFINITY);
        else if(dist2 == null)
            return new PairedReturn<Integer, Double>(0, Double.POSITIVE_INFINITY);
        
        double tmp1, tmp2;
        //Special case: no overlap if there is no overlap between the two distributions,we can easily return a seperating value 
        if( (tmp1 = dist1.invCdf(almost0)) >  (tmp2 = dist2.invCdf(almost1) ) )//If dist1 is completly to the right of dist2
            return new PairedReturn<Integer, Double>(1, (tmp1+tmp2)*0.5);
        else if( (tmp1 = dist1.invCdf(almost1)) <  (tmp2 = dist2.invCdf(almost0) ) )//If dist2 is completly to the right of dist1
            return new PairedReturn<Integer, Double>(0, (tmp1+tmp2)*0.5);
        
        //Define a function we would like to find the root of. There may be multiple roots, but we will only use one. 
        Function f = new Function() {

            /**
			 * 
			 */
			private static final long serialVersionUID = -8587449421333790319L;

			public double f(double... x)
            {
                return dist1.pdf(x[0]) - dist2.pdf(x[0]);
            }

            public double f(Vec x)
            {
                return dist1.pdf(x.get(0)) - dist2.pdf(x.get(0));
            }
        };
        
        double minRange = Math.min(dist1.mean(), dist2.mean());
        double maxRange = Math.max(dist1.mean(), dist2.mean());
        
        //use zeroin because it can fall back to bisection in bad cases,
        //and it is very likely that this function will have non diferentiable points 
        double split = Double.POSITIVE_INFINITY;
        try
        {
            split = Zeroin.root(1e-8, minRange, maxRange, f, 0.0);
        }
        catch(ArithmeticException ex)//Was not in the range, so we will use the invCDF to find better values
        {
            minRange = Math.min(dist1.invCdf(almost0), dist2.invCdf(almost0));
            maxRange = Math.max(dist1.invCdf(almost1), dist2.invCdf(almost1));
            
            split = Zeroin.root(1e-8, minRange, maxRange, f, 0.0);
        }
        
        
        double minStnd = Math.min(dist1.standardDeviation(), dist2.standardDeviation());
        
        int left = 0;
        if(dist2.pdf(split-minStnd/2) > dist1.pdf(split-minStnd/2))
            left = 1;
        return new PairedReturn<Integer, Double>(left, split);
    }