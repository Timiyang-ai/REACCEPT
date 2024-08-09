boolean localize(QrCode.Alignment pattern , float guessX , float guessY)
	{
		// sample along the middle. Try to not sample the outside edges which could confuse it
		for (int i = 0; i < arrayY.length; i++) {
			float x = guessX - 1.5f + i*3f/12.0f;
			float y = guessY - 1.5f + i*3f/12.0f;

			gridToImage.compute(x,guessY,pixel);
			arrayX[i] = interpolate.get(pixel.x,pixel.y);
			gridToImage.compute(guessX,y,pixel);
			arrayY[i] = interpolate.get(pixel.x,pixel.y);
		}

		// TODO turn this into an exhaustive search of the array for best up and down point?
		int downX = greatestDown(arrayX);
		if( downX == -1) return false;
		int upX = greatestUp(arrayX,downX);
		if( upX == -1) return false;

		int downY = greatestDown(arrayY);
		if( downY == -1 ) return false;
		int upY = greatestUp(arrayY,downY);
		if( upY == -1 ) return false;

		pattern.moduleFound.x = guessX - 1.5f + (downX+upX)*3f/24.0f;
		pattern.moduleFound.y = guessY - 1.5f + (downY+upY)*3f/24.0f;

		gridToImage.compute((float)pattern.moduleFound.x,(float)pattern.moduleFound.y,pixel);
		pattern.pixel.set(pixel.x,pixel.y);

		return true;
	}