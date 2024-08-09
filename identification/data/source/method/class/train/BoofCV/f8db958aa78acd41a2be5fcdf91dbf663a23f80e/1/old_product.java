public static boolean optimizedParam( List<List<Point2D_F64>> observations ,
										  List<Point2D_F64> grid ,
										  boolean assumeZeroSkew ,
										  ParametersZhang99 initial ,
										  ParametersZhang99 found )
	{
		Zhang99OptimizationFunction func = new Zhang99OptimizationFunction(
				initial.createNew(),assumeZeroSkew , grid,observations);

		UnconstrainedLeastSquares lm = FactoryOptimization.leastSquaresTrustRegion(1,
				RegionStepType.DOG_LEG_FTF,false);
//		UnconstrainedLeastSquares lm = FactoryOptimization.leastSquaresLM(1e-3,true);
//		UnconstrainedLeastSquares lm = FactoryOptimization.leastSquareLevenberg(1e-3);

		double model[] = new double[ initial.size() ];
		initial.convertToParam(assumeZeroSkew,model);

		lm.setFunction(func,null);
		lm.initialize(model,1e-10,1e-25*observations.size());

		if( !UtilOptimize.process(lm,500) ) {
			return false;
		}

		double param[] = lm.getParameters();
		found.setFromParam(assumeZeroSkew,param);

		return true;
	}