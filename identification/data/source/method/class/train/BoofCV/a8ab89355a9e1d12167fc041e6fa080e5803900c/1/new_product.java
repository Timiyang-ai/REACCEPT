public void process( ImageFloat32 input ) {

		scaleSpace.initialize(input);
		detections.reset();

		do {
			// scale from octave to input image
			pixelScaleToInput = scaleSpace.pixelScaleCurrentToInput();

			// detect features in the image
			for (int j = 1; j < scaleSpace.getNumScales()+1; j++) {

				// not really sure how to compute the scale for features found at a particular DoG image
				// using the average resulted in less visually appealing circles in a test image
				sigmaLower  = scaleSpace.computeSigmaScale( j - 1);
				sigmaTarget = scaleSpace.computeSigmaScale( j    );
				sigmaUpper  = scaleSpace.computeSigmaScale( j + 1);

				// grab the local DoG scale space images
				dogLower  = scaleSpace.getDifferenceOfGaussian(j-1);
				dogTarget = scaleSpace.getDifferenceOfGaussian(j  );
				dogUpper  = scaleSpace.getDifferenceOfGaussian(j+1);

				detectFeatures(j);
			}
		} while( scaleSpace.computeNextOctave() );
	}