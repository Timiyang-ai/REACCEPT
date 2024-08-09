public static void equalizeLocalNaive( ImageUInt8 input , int radius , ImageUInt8 output ,
										   int histogram[] )
	{
		int width = 2*radius+1;
		int maxValue = histogram.length-1;

		for( int y = 0; y < input.height; y++ ) {
			// make sure it's inside the image bounds
			int y0 = y-radius;
			int y1 = y+radius+1;
			if( y0 < 0 ) {
				y0 = 0; y1 = width;
				if( y1 > input.height )
					y1 = input.height;
			} else if( y1 > input.height ) {
				y1 = input.height;
				y0 = y1 - width;
				if( y0 < 0 )
					y0 = 0;
			}

			// pixel indexes
			int indexIn = input.startIndex + y*input.stride;
			int indexOut = output.startIndex + y*output.stride;

			for( int x = 0; x < input.width; x++ ) {
				// make sure it's inside the image bounds
				int x0 = x-radius;
				int x1 = x+radius+1;
				if( x0 < 0 ) {
					x0 = 0; x1 = width;
					if( x1 > input.width )
						x1 = input.width;
				} else if( x1 > input.width ) {
					x1 = input.width;
					x0 = x1 - width;
					if( x0 < 0 )
						x0 = 0;
				}

				// compute the local histogram
				localHistogram(input,x0,y0,x1,y1,histogram);

				// only need to compute up to the value of the input pixel
				int inputValue =  input.data[indexIn++] & 0xFF;
				int sum = 0;
				for( int i = 0; i <= inputValue; i++ ) {
					sum += histogram[i];
				}

				int area = (y1-y0)*(x1-x0);
				output.data[indexOut++] = (byte)((sum*maxValue)/area);
			}
		}
	}