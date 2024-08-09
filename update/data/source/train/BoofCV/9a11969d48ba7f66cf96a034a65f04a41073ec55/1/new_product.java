public static List<Contour> contour(ImageUInt8 input, ConnectRule rule, ImageSInt32 output) {
		if( output == null ) {
			output = new ImageSInt32(input.width,input.height);
		} else {
			InputSanityCheck.checkSameShape(input,output);
		}

		LinearContourLabelChang2004 alg = new LinearContourLabelChang2004(rule);
		alg.process(input,output);
		return alg.getContours().toList();
	}