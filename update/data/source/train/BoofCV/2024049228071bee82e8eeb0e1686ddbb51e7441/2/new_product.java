public static WaveletDescription<WlCoef_F32> generate_F32( int I ) {
		if( I != 6 ) {
			throw new IllegalArgumentException("Only 6 is currently supported");
		}

		WlCoef_F32 coef = new WlCoef_F32();

		coef.offsetScaling = -2;
		coef.offsetWavelet = -2;

		coef.scaling = new float[6];
		coef.wavelet = new float[6];

		double sqrt7 = Math.sqrt(7);
		double div = 16.0*Math.sqrt(2);
		coef.scaling[0] = (float)((1.0-sqrt7)/div);
		coef.scaling[1] = (float)((5.0+sqrt7)/div);
		coef.scaling[2] = (float)((14.0+2.0*sqrt7)/div);
		coef.scaling[3] = (float)((14.0-2.0*sqrt7)/div);
		coef.scaling[4] = (float)((1.0-sqrt7)/div);
		coef.scaling[5] = (float)((-3.0+sqrt7)/div);

		coef.wavelet[0] = coef.scaling[5];
		coef.wavelet[1] = -coef.scaling[4];
		coef.wavelet[2] = coef.scaling[3];
		coef.wavelet[3] = -coef.scaling[2];
		coef.wavelet[4] = coef.scaling[1];
		coef.wavelet[5] = -coef.scaling[0];

		return new WaveletDescription<WlCoef_F32>(new BorderIndex1D_Wrap(),
				coef,new WlBorderCoefStandard<WlCoef_F32>(coef));
	}