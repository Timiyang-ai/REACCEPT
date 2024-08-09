public static void applyPixelNormalization(DenseMatrix64F N, Point2D_F64 orig, Point2D_F64 normed) {
		normed.x = orig.x * N.data[0] + N.data[2];
		normed.y = orig.y * N.data[4] + N.data[5];
	}