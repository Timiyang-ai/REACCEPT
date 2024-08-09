protected static float distanceSq( float[] a , float []b ) {
		float distanceSq = 0;
		for( int i = 0; i < a.length; i++ )  {
			float d = a[i]-b[i];
			distanceSq += d*d;
		}
		return distanceSq;
	}