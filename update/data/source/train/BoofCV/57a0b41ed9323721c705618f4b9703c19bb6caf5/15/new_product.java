public static GrayU8 threshold(GrayS16 input , GrayU8 output ,
								   int threshold , boolean down )
	{
		output = InputSanityCheck.checkDeclare(input,output,GrayU8.class);

		if( down ) {
			for( int y = 0; y < input.height; y++ ) {
				int indexIn = input.startIndex + y*input.stride;
				int indexOut = output.startIndex + y*output.stride;

				int end = indexIn + input.width;

				for( ; indexIn < end; indexIn++ , indexOut++ ) {
					if( (input.data[indexIn]) <= threshold )
						output.data[indexOut] = 1;
					else
						output.data[indexOut] = 0;
				}
			}
		} else {
			for( int y = 0; y < input.height; y++ ) {
				int indexIn = input.startIndex + y*input.stride;
				int indexOut = output.startIndex + y*output.stride;

				int end = indexIn + input.width;

				for( ; indexIn < end; indexIn++ , indexOut++ ) {
					if( (input.data[indexIn]) > threshold )
						output.data[indexOut] = 1;
					else
						output.data[indexOut] = 0;
				}
			}
		}

		return output;
	}