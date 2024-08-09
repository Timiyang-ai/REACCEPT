public static void vertical(Kernel1D_S32 kernel, GrayU8 image, GrayI8 dest , int skip ) {
		checkParameters(image, dest, skip);

		if( kernel.width >= image.width ) {
			ConvolveDownNormalizedNaive.vertical(kernel,image,dest,skip);
		} else {
			ConvolveDownNoBorder.vertical(kernel,image,dest,skip,kernel.computeSum());
			ConvolveDownNormalized_JustBorder.vertical(kernel,image,dest,skip);
		}
	}