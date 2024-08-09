private void trilinearInterpolation( float weight , float sampleX , float sampleY , double angle )
	{
		for (int i = 0; i < widthGrid; i++) {
			double weightGridY = 1.0 - Math.abs(sampleY-i);
			if( weightGridY <= 0) continue;
			for (int j = 0; j < widthGrid; j++) {
				double weightGridX = 1.0 - Math.abs(sampleX-j);
				if( weightGridX <= 0 ) continue;
				for (int k = 0; k < numHistogramBins; k++) {
					double angleBin = k*histogramBinWidth;
					double weightHistogram = 1.0 - UtilAngle.dist(angle,angleBin)/histogramBinWidth;
					if( weightHistogram <= 0 ) continue;

					int descriptorIndex = (i*widthGrid + j)*numHistogramBins + k;
					descriptor.value[descriptorIndex] += weight*weightGridX*weightGridY*weightHistogram;
				}
			}
		}
	}