protected void createMergeList(ImageSInt32 pixelToRegion, GrowQueue_F32 regionColor) {
		// merge the merge list as initial all no merge
		mergeList.resize(regionColor.getSize());
		for( int i = 0; i < mergeList.size; i++ ) {
			mergeList.data[i] = -1;
		}

		// the inner image, excluding the right and bottom borders
		for( int y = 0; y < pixelToRegion.height-1; y++ ) {
			int pixelIndex = y*pixelToRegion.width;
			for( int x = 0; x < pixelToRegion.width-1; x++ , pixelIndex++) {
				int a = pixelToRegion.data[pixelIndex];
				int b = pixelToRegion.data[pixelIndex+1]; // pixel +1 x
				int c = pixelToRegion.data[pixelIndex+pixelToRegion.width]; // pixel +1 y

				float colorA = regionColor.get(a);

				if( a != b ) {
					float colorB = regionColor.get(b);
					boolean merge = Math.abs(colorA-colorB) <= tolerance;
					if( merge ) {
						checkMerge(a,b);
					}
				}

				if( a != c ) {
					float colorC = regionColor.get(c);
					boolean merge = Math.abs(colorA-colorC) <= tolerance;
					if( merge ) {
						checkMerge(a,c);
					}
				}
			}
		}

		// right side of the image
		for( int y = 0; y < pixelToRegion.height-1; y++ ) {
			// location (w-1,y)
			int pixelIndex = y*pixelToRegion.width+pixelToRegion.width-1;

			int a = pixelToRegion.data[pixelIndex];
			int c = pixelToRegion.data[pixelIndex+pixelToRegion.width]; // pixel +1 y

			float colorA = regionColor.get(a);

			if( a != c ) {
				float colorC = regionColor.get(c);
				boolean merge = Math.abs(colorA-colorC) <= tolerance;
				if( merge ) {
					checkMerge(a,c);
				}
			}
		}

		// bottom of the image
		for( int x = 0; x < pixelToRegion.width-1; x++ ) {
			// location (x,h-1)
			int pixelIndex = (pixelToRegion.height-1)*pixelToRegion.width + x;

			int a = pixelToRegion.data[pixelIndex];
			int b = pixelToRegion.data[pixelIndex+1]; // pixel +1 x

			float colorA = regionColor.get(a);

			if( a != b ) {
				float colorB = regionColor.get(b);
				boolean merge = Math.abs(colorA-colorB) <= tolerance;
				if( merge ) {
					checkMerge(a,b);
				}
			}
		}
	}