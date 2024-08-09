public void horizontal(ImageSingleBand img, ImageSingleBand dest) {
		Kernel1D ker = FactoryKernelGaussian.gaussian1D(img.getClass(),-1,kernelRadius);

		// standard symmetric odd kernel
		GImageMiscOps.fill(dest,0);
		invokeMethod("horizontal", ker, img, dest);
		double expected = horizontal(1,1,img,ker,kernelRadius,2*kernelRadius+1);
		assertEquals(expected, get(dest, 1, 1), 1e-6);
		// horizontal border check
		assertEquals(0, get(dest, 0, 3), 1e-6);
		assertEquals(0, get(dest, width-1, 3), 1e-6);

		// non-symmetric kernel
		GImageMiscOps.fill(dest,0);
		((Kernel1D)ker).offset=0;
		invokeMethod("horizontal", ker, img, dest);
		expected = horizontal(1,1,img,ker,0,2*kernelRadius+1);
		assertEquals(expected, get(dest, 1, 1), 1e-6);
		// horizontal border check
		assertTrue(0 != get(dest, 0, 3));
		assertEquals(0, get(dest, width-2, 3), 1e-6);
		assertEquals(0, get(dest, width-1, 3), 1e-6);
	}