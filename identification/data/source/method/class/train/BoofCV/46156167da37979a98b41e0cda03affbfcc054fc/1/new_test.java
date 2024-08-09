@Test
	public void generate_F32() {

		for( int i = 6; i <= 6; i += 2 ) {

			WaveletCoefficient_F32 desc = FactoryWaveletCoiflet.generate_F32(i);

			double sumScaling = UtilWavelet.sumCoefficients(desc.scaling);
			double sumWavelet = UtilWavelet.sumCoefficients(desc.wavelet);

			assertEquals(Math.sqrt(2),sumScaling,1e-4);
			assertEquals(0,sumWavelet,1e-4);

			double energyScaling = UtilWavelet.computeEnergy(desc.scaling);
			double energyWavelet = UtilWavelet.computeEnergy(desc.wavelet);

			assertEquals(1,energyScaling,1e-4);
			assertEquals(1,energyWavelet,1e-4);

			int polyOrder = i/2-1;

			checkPolySumToZero(desc.scaling, polyOrder,-2);
			checkPolySumToZero(desc.wavelet, polyOrder-1,0);

			for( int offset = 0; offset <= 4; offset += 2 ) {
				checkBiorthogonal(offset,desc.scaling,desc.offsetScaling,desc.scaling,desc.offsetScaling);
				checkBiorthogonal(offset,desc.wavelet,desc.offsetWavelet,desc.wavelet,desc.offsetWavelet);
			}
		}
	}