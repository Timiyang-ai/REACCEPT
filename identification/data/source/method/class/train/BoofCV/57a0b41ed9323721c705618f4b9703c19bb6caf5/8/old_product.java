public static void adjustForDisplay( ImageSingleBand transform , int numLevels , double valueRange ) {
		if( transform instanceof ImageFloat32 )
			adjustForDisplay((ImageFloat32)transform,numLevels,(float)valueRange);
		else
			adjustForDisplay((ImageInteger)transform,numLevels,(int)valueRange);
	}