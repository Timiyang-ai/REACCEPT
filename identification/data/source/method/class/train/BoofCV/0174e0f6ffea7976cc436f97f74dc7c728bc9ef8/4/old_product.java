public static Point2Transform2_F64 transformPixelToRectNorm(CameraPinholeRadial param,
																DenseMatrix64F rectify,
																DenseMatrix64F rectifyK) {
		return ImplRectifyImageOps_F64.transformPixelToRectNorm(param, rectify, rectifyK);
	}