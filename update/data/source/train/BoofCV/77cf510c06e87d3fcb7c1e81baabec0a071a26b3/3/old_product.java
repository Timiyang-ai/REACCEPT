public static void derivY_I8(ImageInt8 orig,
								 ImageInt16 derivY) {
		InputSanityCheck.checkSameShape(orig, false, derivY, true);
		GradientThree_Standard.derivY_I8(orig, derivY);
	}