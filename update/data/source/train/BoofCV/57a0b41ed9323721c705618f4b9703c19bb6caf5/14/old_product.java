public static void easy( ImageFloat32 image ) {
		// create the detector and descriptors
		DetectDescribePoint<ImageFloat32,BrightFeature> surf = FactoryDetectDescribe.
				surfStable(new ConfigFastHessian(0, 2, 200, 2, 9, 4, 4), null, null,ImageFloat32.class);

		 // specify the image to process
		surf.detect(image);

		System.out.println("Found Features: "+surf.getNumberOfFeatures());
		System.out.println("First descriptor's first value: "+surf.getDescription(0).value[0]);
	}