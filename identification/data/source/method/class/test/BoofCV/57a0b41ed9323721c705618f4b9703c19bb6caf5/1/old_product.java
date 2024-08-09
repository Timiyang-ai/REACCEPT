public static <T extends ImageSingleBand>
	PyramidFloat<T> scaleSpace( double scaleSpace[], Class<T> imageType ) {

		double[] scaleFactors = new double[ scaleSpace.length ];

		for( int i = 0; i < scaleSpace.length; i++ ) {
			scaleFactors[i] = 1;
		}

		// find the amount of blue that it needs to apply at each layer
		double[] sigmas = new double[ scaleSpace.length ];

		sigmas[0] = scaleSpace[0];
		for( int i = 1; i < scaleSpace.length; i++ ) {
			// the desired amount of blur
			double c = scaleSpace[i];
			// the effective amount of blur applied to the last level
			double b = scaleSpace[i-1];
			// the amount of additional blur which is needed
			sigmas[i] = Math.sqrt(c*c-b*b);
		}

		return floatGaussian(scaleFactors,sigmas,imageType);
	}