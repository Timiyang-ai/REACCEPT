protected void spawnGrid(Rectangle2D_F64 prevRect ) {
		// Shrink the rectangle to ensure that all features are entirely contained inside
		spawnRect.p0.x = prevRect.p0.x + featureRadius;
		spawnRect.p0.y = prevRect.p0.y + featureRadius;
		spawnRect.p1.x = prevRect.p1.x - featureRadius;
		spawnRect.p1.y = prevRect.p1.y - featureRadius;

		double spawnWidth = spawnRect.getWidth();
		double spawnHeight = spawnRect.getHeight();

		// try spawning features at evenly spaced points inside the grid
		tracker.setImage(previousImage,previousDerivX,previousDerivY);

		for( int i = 0; i < gridWidth; i++ ) {

			float y = (float)(spawnRect.p0.y + i*spawnHeight/(gridWidth-1));

			for( int j = 0; j < gridWidth; j++ ) {
				float x = (float)(spawnRect.p0.x + j*spawnWidth/(gridWidth-1));

				Track t = tracks[i*gridWidth+j];
				t.klt.x = x;
				t.klt.y = y;

				if( tracker.setDescription(t.klt) ) {
					t.active = true;
				} else {
					t.active = false;
				}
			}
		}
	}