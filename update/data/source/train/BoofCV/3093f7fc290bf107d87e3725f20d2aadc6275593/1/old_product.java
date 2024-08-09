void extractCalibration( Homography2D_F64 Hinv , CameraPinhole c ) {
		CommonOps_DDF3.multTransA(Hinv,W0,tmp);
		CommonOps_DDF3.multTransB(tmp,Hinv,Wi);

		convertW(Wi,c);
	}