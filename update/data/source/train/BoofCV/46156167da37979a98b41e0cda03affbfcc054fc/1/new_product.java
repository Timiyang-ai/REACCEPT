public static WaveletCoefficient_F32 generate_F32( int I ) {
		if( I != 6 ) {
			throw new IllegalArgumentException("Only 6 is currently supported");
		}

		WaveletCoefficient_F32 ret = new WaveletCoefficient_F32();

		ret.offsetScaling = -2;
		ret.offsetWavelet = -2;

		ret.border = new BorderIndex1D_Wrap();
		ret.scaling = new float[6];
		ret.wavelet = new float[6];

		double sqrt7 = Math.sqrt(7);
		double div = 16.0*Math.sqrt(2);
		ret.scaling[0] = (float)((1.0-sqrt7)/div);
		ret.scaling[1] = (float)((5.0+sqrt7)/div);
		ret.scaling[2] = (float)((14.0+2.0*sqrt7)/div);
		ret.scaling[3] = (float)((14.0-2.0*sqrt7)/div);
		ret.scaling[4] = (float)((1.0-sqrt7)/div);
		ret.scaling[5] = (float)((-3.0+sqrt7)/div);

		ret.wavelet[0] = ret.scaling[5];
		ret.wavelet[1] = -ret.scaling[4];
		ret.wavelet[2] = ret.scaling[3];
		ret.wavelet[3] = -ret.scaling[2];
		ret.wavelet[4] = ret.scaling[1];
		ret.wavelet[5] = -ret.scaling[0];

		return ret;
	}