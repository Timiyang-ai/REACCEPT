protected void updateTrackLocation(ImageFloat32 image) {
		get_subwindow(image, region.tl_x, region.tl_y, subInput);

		// calculate response of the classifier at all locations
		// k = dense_gauss_kernel(sigma, x, z);
		dense_gauss_kernel(sigma, subPrev,subInput,k);
		fft.forward(k,kf);

		// response = real(ifft2(alphaf .* fft2(k)));   %(Eq. 9)
		DiscreteFourierTransformOps.multiplyComplex(alphaf, kf, tmpFourier0);
		fft.inverse(tmpFourier0, tmpReal0);

		// find the pixel with the largest response
		int N = tmpReal0.width*tmpReal0.height;
		int indexBest = -1;
		float valueBest = -1;
		for( int i = 0; i < N; i++ ) {
			float v = tmpReal0.data[i];
			if( v > valueBest ) {
				valueBest = v;
				indexBest = i;
			}
		}

		// peak in region's coordinate system
		int deltaX = subInput.width/2 - (indexBest % tmpReal0.width);
		int deltaY = subInput.height/2 - (indexBest / tmpReal0.width);

		// convert peak location into image coordinate system
		region.tl_x = region.tl_x + deltaX;
		region.tl_y = region.tl_y + deltaY;

		ensureInBounds(region,image.width,image.height);
	}