protected void spawnGrid(RectangleCorner2D_F64 prevRect ) {
		// Shrink the rectangle to ensure that all features are entirely contained inside
		spawnRect.x0 = prevRect.x0 + featureRadius;
		spawnRect.y0 = prevRect.y0 + featureRadius;
		spawnRect.x1 = prevRect.x1 - featureRadius;
		spawnRect.y1 = prevRect.y1 - featureRadius;

		double spawnWidth = spawnRect.getWidth();
		double spawnHeight = spawnRect.getHeight();

		// try spawning features at evenly spaced points inside the grid
		tracker.setImage(previousImage,previousDerivX,previousDerivY);

		for( int i = 0; i < gridWidth; i++ ) {

			float y = (float)(spawnRect.y0 + i*spawnHeight/(gridWidth-1));

			for( int j = 0; j < gridWidth; j++ ) {
				float x = (float)(spawnRect.x0 + j*spawnWidth/(gridWidth-1));

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