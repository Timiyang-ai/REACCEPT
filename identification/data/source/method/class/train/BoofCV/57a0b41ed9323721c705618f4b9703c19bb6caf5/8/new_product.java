public static void adjustForDisplay(ImageGray transform , int numLevels , double valueRange ) {
		if( transform instanceof GrayF32)
			adjustForDisplay((GrayF32)transform,numLevels,(float)valueRange);
		else
			adjustForDisplay((GrayI)transform,numLevels,(int)valueRange);
	}