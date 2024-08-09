public void equiToLonlat(float x , float y , Point2D_F32 latlon ) {
		latlon.x = (x/width - 0.5f)*GrlConstants.F_PI2; // longitude
		latlon.y = (y/height - 0.5f)*GrlConstants.F_PI; // latitude
	}