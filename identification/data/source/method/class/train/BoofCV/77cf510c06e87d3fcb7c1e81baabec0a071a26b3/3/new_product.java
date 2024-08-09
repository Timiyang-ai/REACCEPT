public static void deriv_I8(ImageUInt8 orig,
								ImageSInt16 derivX,
								ImageSInt16 derivY) {
		InputSanityCheck.checkSameShape(orig, derivX, derivY);
		GradientThree_Standard.deriv_I8(orig, derivX, derivY);
	}