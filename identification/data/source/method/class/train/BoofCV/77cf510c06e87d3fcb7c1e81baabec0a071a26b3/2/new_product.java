public static void derivY_I8(ImageUInt8 orig,
								 ImageSInt16 derivY) {
		InputSanityCheck.checkSameShape(orig, derivY);
		GradientThree_Standard.derivY_I8(orig, derivY);
	}