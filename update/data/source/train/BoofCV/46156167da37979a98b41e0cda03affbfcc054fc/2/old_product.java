public static WaveletDesc_F32 standard_F32( int J ) {
		if( J != 4 ) {
			throw new IllegalArgumentException("Only 4 is currently supported");
		}

		WaveletDesc_F32 ret = new WaveletDesc_F32();

		ret.border = new BorderIndex1D_Wrap();
		ret.offsetScaling = 0;
		ret.offsetWavelet = 0;

		ret.scaling = new float[4];
		ret.wavelet = new float[4];

		double sqrt3 = Math.sqrt(3);
		double div = 4.0*Math.sqrt(2);
		ret.scaling[0] = (float)((1+sqrt3)/div);
		ret.scaling[1] = (float)((3+sqrt3)/div);
		ret.scaling[2] = (float)((3-sqrt3)/div);
		ret.scaling[3] = (float)((1-sqrt3)/div);

		ret.wavelet[0] = ret.scaling[3];
		ret.wavelet[1] = -ret.scaling[2];
		ret.wavelet[2] = ret.scaling[1];
		ret.wavelet[3] = -ret.scaling[0];

		return ret;
	}