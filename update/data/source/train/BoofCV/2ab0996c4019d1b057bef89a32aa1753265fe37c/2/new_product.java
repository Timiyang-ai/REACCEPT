void scoreDirection(int dx , int dy ) {
		// TODO fully support disparityMin here
		if( dx > 0 ) {
			for (int y = 0; y < lengthY; y++) {
				scorePath(disparityMin,y,dx,dy);
			}
		} else if( dx < 0 ) {
			for (int y = 0; y < lengthY; y++) {
				scorePath(lengthX-1,y,dx,dy);
			}
		}
		if( dy > 0 ) {
			int x0 = dx > 0 ? 1 : 0;
			int x1 = dx < 0 ? lengthX-1 : lengthX;
			for (int x = x0; x < x1; x++) {
				scorePath(x,0,dx,dy);
			}
		} else if( dy < 0 ) {
			int x0 = dx > 0 ? 1 : 0;
			int x1 = dx < 0 ? lengthX-1 : lengthX;
			for (int x = x0; x < x1; x++) {
				scorePath(x,lengthY-1,dx,dy);
			}
		}
	}