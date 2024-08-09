public static void derivX_I8(ImageInt8 orig,
								 ImageInt16 derivX) {
		InputSanityCheck.checkSameShape(orig, false, derivX, true);
		GradientThree_Standard.derivX_I8(orig, derivX);
	}