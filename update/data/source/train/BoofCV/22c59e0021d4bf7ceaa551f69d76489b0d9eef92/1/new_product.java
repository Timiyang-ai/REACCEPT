protected void computeHistogramBorder(T image, RectangleRotate_F32 region) {
		for( int i = 0; i < samplePts.size(); i++ ) {
			Point2D_F32 p = samplePts.get(i);

			squareToImageSample(p.x, p.y, region);

			// make sure its inside the image
			if( !BoofMiscOps.checkInside(image, imageX, imageY)) {
				sampleHistIndex[ i ] = -1;
			} else {
				// use the slower interpolation which can handle the border
				interpolate.get(imageX, imageY, value);

				int indexHistogram = computeHistogramBin(value);

				sampleHistIndex[ i ] = indexHistogram;
				histogram[indexHistogram] += weights[i];
			}
		}
	}