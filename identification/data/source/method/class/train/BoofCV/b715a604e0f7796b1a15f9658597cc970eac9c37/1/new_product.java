public void process( double c_x , double c_y , double scale , double orientation ,
						 int imageIndex ,
						 double pixelScale ,
						 BrightFeature desc )
	{
		image = ss.getPyramidLayer(imageIndex);
		derivX = ss.getDerivativeX(imageIndex);
		derivY = ss.getDerivativeY(imageIndex);

		for( int i = 0; i < desc.value.length; i++ )
			desc.value[i] = 0;
		for( int i = 0; i < histograms.length; i++ )
			for( int j = 0; j < histograms[i].length; j++ )
				histograms[i][j] = 0;

		// account for the scale of the image in each octave
		constructHistograms(c_x/pixelScale , c_y/pixelScale, scale/pixelScale, orientation );

		computeDescriptor(desc);
	}