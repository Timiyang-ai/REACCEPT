protected boolean localize(QrCode.Alignment pattern , float guessX , float guessY)
	{
		// sample along the middle. Try to not sample the outside edges which could confuse it
		for (int i = 0; i < arrayY.length; i++) {
			float x = guessX - 1.25f + i*2.5f/10.0f;
			float y = guessY - 1.25f + i*2.5f/10.0f;
			arrayX[i] = interpolate.get(x,guessY);
			arrayY[i] = interpolate.get(guessX,y);
		}

		int downX = greatestDown(arrayX);
		int upX = greatestUp(arrayX,downX);

		if( downX == -1 || upX == -1)
			return false;

		int downY = greatestDown(arrayY);
		int upY = greatestUp(arrayY,downY);

		if( downY == -1 || upY == -1)
			return false;

		pattern.moduleFound.x = guessX - 1.25f + (downX+upX)*2.5f/20.0f;
		pattern.moduleFound.y = guessY - 1.25f + (downY+upY)*2.5f/20.0f;

		gridToImage.compute((float)pattern.moduleFound.x,(float)pattern.moduleFound.y,p32);
		pattern.pixel.set(p32.x,p32.y);

		return true;
	}