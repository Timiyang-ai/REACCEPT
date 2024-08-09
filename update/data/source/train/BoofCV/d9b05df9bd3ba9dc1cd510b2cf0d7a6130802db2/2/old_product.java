public static void horizontal(Kernel1D_I32 kernel, GrayS16 image, GrayI16 dest , int skip ) {
		checkParameters(image, dest, skip);

		if( kernel.width >= image.width ) {
			ConvolveDownNormalizedNaive.horizontal(kernel,image,dest,skip);
		} else {
			ConvolveDownNoBorder.horizontal(kernel,image,dest,skip,kernel.computeSum());
			ConvolveDownNormalized_JustBorder.horizontal(kernel,image,dest,skip);
		}
	}