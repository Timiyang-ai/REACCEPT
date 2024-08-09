public static Point2Transform2_F32 transformPixelToRectNorm(CameraPinholeRadial param,
																DenseMatrix32F rectify,
																DenseMatrix32F rectifyK) {
		return ImplRectifyImageOps_F32.transformPixelToRectNorm(param, rectify, rectifyK);
	}