public static void down( ImageFloat32 input , int sampleWidth , ImageFloat32 output ) {
		if( sampleWidth == 2 ) {
			ImplAverageDownSample2.down( input, output);
		} else {
			ImplAverageDownSampleN.down( input, sampleWidth , output);
		}
	}