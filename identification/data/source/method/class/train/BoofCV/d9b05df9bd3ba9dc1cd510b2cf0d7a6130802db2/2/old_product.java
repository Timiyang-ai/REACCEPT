public static void convolve(Kernel2D_I32 kernel, GrayS16 image, GrayI16 dest , int skip ) {
		checkParameters(image, dest, skip);

		if( kernel.width >= image.width ) {
			ConvolveDownNormalizedNaive.convolve(kernel,image,dest,skip);
		} else {
			ConvolveDownNoBorder.convolve(kernel,image,dest,skip,kernel.computeSum());
			ConvolveDownNormalized_JustBorder.convolve(kernel,image,dest,skip);
		}
	}