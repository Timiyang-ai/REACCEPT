protected void computeClusterDistance() {
		for( int i = 0; i < pixels.size; i++ ) {
			pixels.data[i].reset();
		}

		for( int i = 0; i < clusters.size; i++ ) {
			Cluster c = clusters.data[i];

			// compute search bounds
			int centerX = (int)(c.x + 0.5f);
			int centerY = (int)(c.y + 0.5f);

			int x0 = centerX - gridInterval; int x1 = centerX + gridInterval + 1;
			int y0 = centerY - gridInterval; int y1 = centerY + gridInterval + 1;

			if( x0 < 0 ) x0 = 0;
			if( y0 < 0 ) y0 = 0;
			if( x1 > input.width ) x1 = input.width;
			if( y1 > input.height ) y1 = input.height;

			for( int y = y0; y < y1; y++ ) {
				int indexPixel = y*input.width + x0;
				int indexInput = input.startIndex + y*input.stride + x0;

				int dy = y-centerY;

				for( int x = x0; x < x1; x++ ) {
					int dx = x-centerX;

					float distanceColor = colorDistance(c.color,indexInput++);
					float distanceSpacial = dx*dx + dy*dy;
					pixels.data[indexPixel++].add(c,distanceColor + adjustSpacial*distanceSpacial);
				}
			}

			// update each pixel inside the search rectangle
			clusters.data[i].reset();
		}
	}