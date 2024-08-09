@Test
	public void extractCalibration() {
		// camera calibration matrix
		Homography2D_F64 K1 = new Homography2D_F64(300,1,320,0,305,330,0,0,1);
		// W = K'*K
		Homography2D_F64 W1 = new Homography2D_F64();
		CommonOps_DDF3.multTransA(K1,K1,W1);

		Se3_F64 v1_to_v0 = SpecialEuclideanOps_F64.eulerXyz(1,0,0.1,0.1,0.05,-0.09,null);
		DMatrixRMaj H = MultiViewOps.createHomography(v1_to_v0.R,v1_to_v0.T,1,new Vector3D_F64(0,0,1));
		Homography2D_F64 H1 = new Homography2D_F64();
		Homography2D_F64 H1_inv = new Homography2D_F64();
		ConvertDMatrixStruct.convert(H,H1);
		CommonOps_DDF3.invert(H1,H1_inv);

		Homography2D_F64 tmp = new Homography2D_F64();
		Homography2D_F64 W0 = new Homography2D_F64();
		CommonOps_DDF3.multTransA(H1,W1,tmp);
		CommonOps_DDF3.mult(tmp,H1,W0);

		SelfCalibrationLinearRotationMulti alg = new SelfCalibrationLinearRotationMulti();
		alg.setConstraints(false,false,false,-1);
		alg.W0.set(W0);

		CameraPinhole found = new CameraPinhole();
		alg.extractCalibration(H1_inv,found);

		assertEquals(K1.a11,found.fx, UtilEjml.TEST_F64_SQ);
		assertEquals(K1.a12,found.skew, UtilEjml.TEST_F64_SQ);
		assertEquals(K1.a13,found.cx, UtilEjml.TEST_F64_SQ);
		assertEquals(K1.a22,found.fy, UtilEjml.TEST_F64_SQ);
		assertEquals(K1.a23,found.cy, UtilEjml.TEST_F64_SQ);
	}