public static int[] selectPyramidScale( int imageWidth , int imageHeight, int minSize ) {
		int w = Math.max(imageWidth,imageHeight);

		int maxScale = w/minSize;
		int n = 1;
		int scale = 1;
		while( scale*2 < maxScale ) {
			n++;
			scale *= 2;
		}

		int ret[] = new int[n];
		scale = 1;
		for( int i = 0; i < n; i++ ) {
			ret[i] = scale;
			scale *= 2;
		}

		return ret;
	}