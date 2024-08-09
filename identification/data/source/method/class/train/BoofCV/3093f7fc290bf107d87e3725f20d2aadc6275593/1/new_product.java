void extractCalibration( Homography2D_F64 Hinv , CameraPinhole c ) {
		CommonOps_DDF3.multTransA(Hinv,W0,tmp);
		CommonOps_DDF3.mult(tmp,Hinv,Wi);

		convertW(Wi,c);
	}