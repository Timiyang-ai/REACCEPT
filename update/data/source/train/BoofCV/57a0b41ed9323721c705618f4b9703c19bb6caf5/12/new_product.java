public static void down(GrayF32 input , int sampleWidth , GrayF32 output ) {
		if( sampleWidth == 2 ) {
			ImplAverageDownSample2.down( input, output);
		} else {
			ImplAverageDownSampleN.down( input, sampleWidth , output);
		}
	}