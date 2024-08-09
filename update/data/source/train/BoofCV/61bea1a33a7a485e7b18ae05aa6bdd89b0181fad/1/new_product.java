public boolean optimizedParam( List<List<Point2D_F64>> observations ,
								   List<Point2D_F64> grid ,
								   Zhang99ParamAll initial ,
								   Zhang99ParamAll found ,
								   UnconstrainedLeastSquares optimizer )
	{
		if( optimizer == null ) {
//			optimizer = FactoryOptimization.leastSquaresTrustRegion(1,
//					RegionStepType.DOG_LEG_FTF,true);
			optimizer = FactoryOptimization.leastSquaresLM(1e-3,true);
//			optimizer = FactoryOptimization.leastSquareLevenberg(1e-3);
		}

		double model[] = new double[ initial.numParameters() ];
		initial.convertToParam(model);

		Zhang99OptimizationFunction func = new Zhang99OptimizationFunction(
				initial.createNew(), grid,observations);

// Both the numerical and analytical Jacobian appear to provide the same results, but the
// unit test tolerance is so crude that I trust the numerical Jacobian more
//		Zhang99OptimizationJacobian jacobian = new Zhang99OptimizationJacobian(
//				initial.assumeZeroSkew,initial.distortion.length,observations.size(),grid);

		optimizer.setFunction(func,null);
		optimizer.initialize(model,1e-10,1e-25*observations.size());

		for( int i = 0; i < 500; i++ ) {
			if( optimizer.iterate() ) {
				break;
			} else {
				if( i % 25 == 0 )
					status("Progress "+(100*i/500.0)+"%");
			}
		}

		double param[] = optimizer.getParameters();
		found.setFromParam(param);

		return true;
	}