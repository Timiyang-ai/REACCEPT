public static WaveletDescription<WlCoef_F32> daubJ_F32( int J ) {
		if( J != 4 ) {
			throw new IllegalArgumentException("Only 4 is currently supported");
		}

		WlCoef_F32 coef = new WlCoef_F32();

		coef.offsetScaling = 0;
		coef.offsetWavelet = 0;

		coef.scaling = new float[4];
		coef.wavelet = new float[4];

		double sqrt3 = Math.sqrt(3);
		double div = 4.0*Math.sqrt(2);
		coef.scaling[0] = (float)((1+sqrt3)/div);
		coef.scaling[1] = (float)((3+sqrt3)/div);
		coef.scaling[2] = (float)((3-sqrt3)/div);
		coef.scaling[3] = (float)((1-sqrt3)/div);

		coef.wavelet[0] = coef.scaling[3];
		coef.wavelet[1] = -coef.scaling[2];
		coef.wavelet[2] = coef.scaling[1];
		coef.wavelet[3] = -coef.scaling[0];

		return new WaveletDescription<WlCoef_F32>(new BorderIndex1D_Wrap(),
		coef,new WlBorderCoefStandard<WlCoef_F32>(coef));
	}