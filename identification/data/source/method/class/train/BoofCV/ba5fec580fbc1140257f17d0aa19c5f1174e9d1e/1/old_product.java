static boolean sanityCheckConvex( List<Point2D_I32> contour , int indexA , int indexB )
	{
		double d = Math.sqrt(distanceSq(contour.get(indexA),contour.get(indexB)));

		int maxAllowed = (int)(2*Math.PI*d+0.5);

		if( indexB-indexA > maxAllowed )
			return false;
		if( indexA + contour.size()-indexB > maxAllowed )
			return false;

		return true;
	}