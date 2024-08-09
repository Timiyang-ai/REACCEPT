public static Point2Transform2_F64 transformPixelToRectNorm(CameraPinholeBrown param,
																DMatrixRMaj rectify,
																DMatrixRMaj rectifyK) {
		return ImplRectifyImageOps_F64.transformPixelToRectNorm(param, rectify, rectifyK);
	}