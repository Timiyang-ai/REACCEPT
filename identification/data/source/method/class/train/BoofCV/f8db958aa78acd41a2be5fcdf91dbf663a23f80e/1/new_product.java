public static boolean optimizedParam( List<List<Point2D_F64>> observations ,
										  List<Point2D_F64> grid ,
										  boolean assumeZeroSkew ,
										  ParametersZhang99 initial ,
										  ParametersZhang99 found ,
										  UnconstrainedLeastSquares optimizer )
	{
		Zhang99OptimizationFunction func = new Zhang99OptimizationFunction(
				initial.createNew(),assumeZeroSkew , grid,observations);

		if( optimizer == null ) {
//			optimizer = FactoryOptimization.leastSquaresTrustRegion(1,
//					RegionStepType.DOG_LEG_FTF,true);
			optimizer = FactoryOptimization.leastSquaresLM(1e-3,true);
//			optimizer = FactoryOptimization.leastSquareLevenberg(1e-3);
		}

		double model[] = new double[ initial.size() ];
		initial.convertToParam(assumeZeroSkew,model);

		optimizer.setFunction(func,null);
		optimizer.initialize(model,1e-10,1e-25*observations.size());

		if( !UtilOptimize.process(optimizer,500) ) {
			return false;
		}

		double param[] = optimizer.getParameters();
		found.setFromParam(assumeZeroSkew,param);

		return true;
	}