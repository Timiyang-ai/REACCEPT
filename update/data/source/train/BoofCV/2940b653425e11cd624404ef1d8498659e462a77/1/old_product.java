public boolean optimizedParam( List<CalibrationObservation> observations ,
								   List<Point2D_F64> grid ,
								   Zhang99AllParam initial ,
								   Zhang99AllParam found ,
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
				initial.createLike(), grid,observations);

		Zhang99OptimizationJacobian jacobian = new Zhang99OptimizationJacobian(
				(CalibParamPinholeRadial)found.getIntrinsic(), observations,grid);

		optimizer.setFunction(func,jacobian);
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