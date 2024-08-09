public static void derivX_I8(ImageUInt8 orig,
								 ImageSInt16 derivX) {
		InputSanityCheck.checkSameShape(orig, derivX);
		GradientThree_Standard.derivX_I8(orig, derivX);
	}