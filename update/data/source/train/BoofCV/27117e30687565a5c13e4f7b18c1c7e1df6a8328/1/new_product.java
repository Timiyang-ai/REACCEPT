public void convolve(ImageSingleBand img, ImageSingleBand dest) {
		Object ker;
		if (!img.getDataType().isInteger()) {
			if( img.getDataType().getNumBits() == 32 )
				ker = FactoryKernel.random2D_F32(kernelWidth,kernelRadius, 0f, 1f, new Random(234));
			else
				ker = FactoryKernel.random2D_F64(kernelWidth,kernelRadius, 0f, 1f, new Random(234));
		} else
			ker = FactoryKernel.random2D_I32(kernelWidth,kernelRadius, 0, 10, new Random(234));

		// standard symmetric kernel
		convolve(img, dest, ker);

		// non-symmetric kernel
		((KernelBase)ker).offset = 0;
		convolve(img, dest, ker);
	}