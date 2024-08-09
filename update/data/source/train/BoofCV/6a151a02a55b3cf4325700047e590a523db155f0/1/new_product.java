public void process( T input ) {
//		System.out.println("ENTER CHESSBOARD CORNER "+input.width+" x "+input.height);
		borderImg.setImage(input);
		gradient.process(input,derivX,derivY);

		interpX.setImage(derivX);
		interpY.setImage(derivY);

		cornerIntensity.process(derivX,derivY,intensity);
		intensityInterp.setImage(intensity);

		// adjust intensity value so that its between 0 and levels for OTSU thresholding
		float featmax = ImageStatistics.max(intensity);
		PixelMath.multiply(intensity, GRAY_LEVELS /featmax,intensity);

//		int N = intensity.width*input.height;
//		for (int i = 0; i < N; i++) {
//			if( intensity.data[i] <= 2f ) {
//				intensity.data[i] = 0f;
//			}
//		}

		// convert into a binary image with high feature intensity regions being 1
		inputToBinary.process(intensity,binary);
		// find the small regions. Th se might be where corners are
		contourFinder.process(binary);

		corners.reset();
		List<ContourPacked> packed = contourFinder.getContours();
//		System.out.println("  * features.size = "+packed.size());
		for (int i = 0; i < packed.size(); i++) {
			contourFinder.loadContour(i,contour);

			ChessboardCorner c = corners.grow();

			UtilPoint2D_I32.mean(contour.toList(),c);
			// compensate for the bias caused by how pixels are counted.
			// Example: a 4x4 region is expected. Center should be at (2,2) but will instead be (1.5,1.5)
			c.x += 0.5;
			c.y += 0.5;

			computeFeatures(c.x,c.y,c);

//			System.out.println("radius = "+radius+" angle = "+c.angle);
//			System.out.println("intensity "+c.intensity);
			if( c.intensity < cornerIntensityThreshold ) {
				corners.removeTail();
			} else if( useMeanShift ) {
				meanShiftLocation(c);
				// TODO does it make sense to use mean shift first?
				computeFeatures(c.x,c.y,c);
			}
		}

//		System.out.println("Dropped "+dropped+" / "+packed.size());
	}