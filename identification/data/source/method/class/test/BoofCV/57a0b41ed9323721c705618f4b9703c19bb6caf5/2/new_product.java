public static void convolve(Kernel2D_F32 kernel, GrayF32 image, GrayF32 dest , int skip ) {
		checkParameters(image, dest, skip);

		if( kernel.width >= image.width ) {
			ConvolveDownNormalizedNaive.convolve(kernel,image,dest,skip);
		} else {
			ConvolveDownNoBorder.convolve(kernel,image,dest,skip);
			ConvolveDownNormalized_JustBorder.convolve(kernel,image,dest,skip);
		}
	}