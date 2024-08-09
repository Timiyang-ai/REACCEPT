public static Point2Transform2_F64 transformPixelToRectNorm(CameraPinholeRadial param,
																RowMatrix_F64 rectify,
																RowMatrix_F64 rectifyK) {
		return ImplRectifyImageOps_F64.transformPixelToRectNorm(param, rectify, rectifyK);
	}