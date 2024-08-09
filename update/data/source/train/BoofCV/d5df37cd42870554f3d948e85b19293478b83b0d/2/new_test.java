@Test
	public void transformPixelToRectNorm_F64() {
		CameraPinholeBrown param = new CameraPinholeBrown().
						fsetK(300, 320, 0, 150, 130, width, height).fsetRadial(0.1,1e-4);

		DMatrixRMaj rect = new DMatrixRMaj(3,3,true,1.1,0,0,0,2,0,0.1,0,3);
		DMatrixRMaj rectK = PerspectiveOps.pinholeToMatrix(param, (DMatrixRMaj)null);

		DMatrixRMaj rectK_inv = new DMatrixRMaj(3,3);
		CommonOps_DDRM.invert(rectK,rectK_inv);

		Point2Transform2_F64 tranRect = RectifyImageOps.transformPixelToRect(param, rect);
		Point2Transform2_F64 alg = RectifyImageOps.transformPixelToRectNorm(param, rect, rectK);

		double x=10,y=20;

		// compute expected results
		Point2D_F64 rectified = new Point2D_F64();
		tranRect.compute(x,y,rectified);
		Point2D_F64 expected = new Point2D_F64();
		GeometryMath_F64.mult(rectK_inv,new Point2D_F64(rectified.x,rectified.y),expected);

		// compute the 'found' results
		Point2D_F64 found = new Point2D_F64();
		alg.compute(x,y,found);

		assertEquals(expected.x, found.x, 1e-4);
		assertEquals(expected.y, found.y, 1e-4);
	}