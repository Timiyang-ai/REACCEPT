public static void deriv_I8(ImageInt8 orig,
								ImageInt16 derivX,
								ImageInt16 derivY) {
		InputSanityCheck.checkSameShape(orig, derivX, derivY);
		GradientThree_Standard.deriv_I8(orig, derivX, derivY);
	}