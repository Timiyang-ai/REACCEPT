@Test
	public void daubJ_F32_forward() {
		for( int i = 4; i <= 4; i += 2 ) {

			// test forward coefficients for the expected properties
			WlCoef_F32 forwardCoef = FactoryWaveletDaub.daubJ_F32(i).forward;

			double sumScaling = UtilWavelet.sumCoefficients(forwardCoef.scaling);
			double sumWavelet = UtilWavelet.sumCoefficients(forwardCoef.wavelet);

			assertEquals(Math.sqrt(2),sumScaling,1e-4);
			assertEquals(0,sumWavelet,1e-4);

			double energyScaling = UtilWavelet.computeEnergy(forwardCoef.scaling);
			double energyWavelet = UtilWavelet.computeEnergy(forwardCoef.wavelet);

			assertEquals(1,energyScaling,1e-4);
			assertEquals(1,energyWavelet,1e-4);

			int polyOrder = i/2-1;

			checkPolySumToZero(forwardCoef.wavelet, polyOrder,0);

			for( int offset = 0; offset <= 4; offset += 2 ) {
				checkBiorthogonal(offset,forwardCoef.scaling,forwardCoef.offsetScaling,forwardCoef.scaling,forwardCoef.offsetScaling);
				checkBiorthogonal(offset,forwardCoef.wavelet,forwardCoef.offsetWavelet,forwardCoef.wavelet,forwardCoef.offsetWavelet);
			}
		}
	}