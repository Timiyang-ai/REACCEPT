public static void applyMask(GrayU8 disparity , GrayU8 mask , int radius ) {
		if( disparity.isSubimage() || mask.isSubimage() )
			throw new RuntimeException("Input is subimage. Currently not support but no reason why it can't be. Ask for it");

		int N = disparity.width*disparity.height;
		for (int i = 0; i < N; i++) {
			if( mask.data[i] == 0 ) {
				disparity.data[i] = (byte)255;
			}
		}
		// TODO make this more efficient and correct
		int r = radius;
		for (int y = r; y < mask.height-r; y++) {
			int indexMsk = y*mask.stride+r;
			for (int x = r; x < mask.width-r-1; x++,indexMsk++) {
				if( (mask.data[indexMsk] + mask.data[indexMsk+1])%2 != 0 ||
						(mask.data[indexMsk] + mask.data[indexMsk+mask.width])%2 != 0 ) {
					for (int i = -r; i <= r; i++) {
						for (int j = -r; j <= r; j++) {
							disparity.unsafe_set(x+i,y+j,255);
						}
					}
				}
			}
		}
	}