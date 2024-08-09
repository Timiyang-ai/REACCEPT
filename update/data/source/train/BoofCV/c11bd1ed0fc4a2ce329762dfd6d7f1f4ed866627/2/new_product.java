protected void updateTrackLocation(T image) {
		get_subwindow(image, templateNew);

		// calculate response of the classifier at all locations
		// matlab: k = dense_gauss_kernel(sigma, x, z);
		dense_gauss_kernel(sigma, templateNew, template,k);

		fft.forward(k,kf);

		// response = real(ifft2(alphaf .* fft2(k)));   %(Eq. 9)
		DiscreteFourierTransformOps.multiplyComplex(alphaf, kf, tmpFourier0);
		fft.inverse(tmpFourier0, response);

		// find the pixel with the largest response
		int N = response.width*response.height;
		int indexBest = -1;
		double valueBest = -1;
		for( int i = 0; i < N; i++ ) {
			double v = response.data[i];
			if( v > valueBest ) {
				valueBest = v;
				indexBest = i;
			}
		}

		int peakX = indexBest % response.width;
		int peakY = indexBest / response.width;

		// sub-pixel peak estimation
		subpixelPeak(peakX, peakY);

		// peak in region's coordinate system
		float deltaX = (peakX+offX) - templateNew.width/2;
		float deltaY = (peakY+offY) - templateNew.height/2;

		// convert peak location into image coordinate system
		regionTrack.x0 = regionTrack.x0 + deltaX*stepX;
		regionTrack.y0 = regionTrack.y0 + deltaY*stepY;

		updateRegionOut();
	}