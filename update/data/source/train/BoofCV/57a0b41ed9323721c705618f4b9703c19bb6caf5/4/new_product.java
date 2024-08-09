public static List<Contour> contour(GrayU8 input, ConnectRule rule, GrayS32 output) {
		if( output == null ) {
			output = new GrayS32(input.width,input.height);
		} else {
			InputSanityCheck.checkSameShape(input,output);
		}

		LinearContourLabelChang2004 alg = new LinearContourLabelChang2004(rule);
		alg.process(input,output);
		return alg.getContours().toList();
	}