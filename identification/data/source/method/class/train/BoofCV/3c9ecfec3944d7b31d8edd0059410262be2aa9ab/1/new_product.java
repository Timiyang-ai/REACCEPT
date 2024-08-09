public static <T extends ImageBase>
	void features( double c_x , double c_y ,
				   double theta , Kernel2D_F64 weight ,
				   int widthLargeGrid , int widthSubRegion,
				   double scale ,
				   SparseImageGradient<T,?> gradient,
				   double []features )
	{
		int regionSize = widthLargeGrid*widthSubRegion;
		if( weight.width != regionSize ) {
			throw new IllegalArgumentException("Weighting kernel has an unexpected size");
		}

		int regionR = regionSize/2;
		int regionEnd = regionSize-regionR;

		int regionIndex = 0;

		double c = Math.cos(theta);
		double s = Math.sin(theta);

		// when computing the pixel coordinates it is more precise to round to the nearest integer
		// since pixels are always positive round() is equivalent to adding 0.5 and then converting
		// to an int, which floors the variable.
		c_x += 0.5;
		c_y += 0.5;

		// step through the sub-regions
		for( int rY = -regionR; rY < regionEnd; rY += widthSubRegion ) {
			for( int rX = -regionR; rX < regionEnd; rX += widthSubRegion ) {
				double sum_dx = 0, sum_dy=0, sum_adx=0, sum_ady=0;

				// compute and sum up the response  inside the sub-region
				for( int i = 0; i < widthSubRegion; i++ ) {
					double regionY = (rY + i)*scale;
					for( int j = 0; j < widthSubRegion; j++ ) {
						double w = weight.get(regionR+rX + j, regionR+rY + i);

						double regionX = (rX + j)*scale;

						// rotate the pixel along the feature's direction
						int pixelX = (int)(c_x + c*regionX - s*regionY);
						int pixelY = (int)(c_y + s*regionX + c*regionY);

						// compute the wavelet and multiply by the weighting factor
						GradientValue g = gradient.compute(pixelX,pixelY);
						double dx = w*g.getX();
						double dy = w*g.getY();

						// align the gradient along image patch
						// note the transform is transposed
						double pdx =  c*dx + s*dy;
						double pdy = -s*dx + c*dy;

						sum_dx += pdx;
						sum_adx += Math.abs(pdx);
						sum_dy += pdy;
						sum_ady += Math.abs(pdy);
					}
				}
				features[regionIndex++] = sum_dx;
				features[regionIndex++] = sum_adx;
				features[regionIndex++] = sum_dy;
				features[regionIndex++] = sum_ady;
			}
		}
	}