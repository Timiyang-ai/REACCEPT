public void process( Planar<GrayU16> costYXD ) {
		init(costYXD);

		if( pathsConsidered >= 1 ) {
			scoreDirection(1, 0);
		}
		if( pathsConsidered >= 2 ) {
			scoreDirection(-1, 0);
		}
		if( pathsConsidered >= 4 ) {
			scoreDirection(0, 1);
			scoreDirection(0, -1);
		}
		if( pathsConsidered >= 8 ) {
			scoreDirection(1, 1);
			scoreDirection(-1, -1);
			scoreDirection(-1, 1);
			scoreDirection(1, -1);
		}
		if( pathsConsidered >= 16 ) {
			scoreDirection(1, 2);
			scoreDirection(2, 1);
			scoreDirection(2, -1);
			scoreDirection(1, -2);
			scoreDirection(-1, -2);
			scoreDirection(-2, -1);
			scoreDirection(-2, 1);
			scoreDirection(-1, 2);
		}
	}