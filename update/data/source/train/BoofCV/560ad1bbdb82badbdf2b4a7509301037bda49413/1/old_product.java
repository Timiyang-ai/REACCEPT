private void createMergeList(ImageSInt32 pixelToregion, GrowQueue_F32 regionColor) {
		// merge the merge list as initial all no merge
		mergeList.resize(regionColor.getSize());
		for( int i = 0; i < mergeList.size; i++ ) {
			mergeList.data[i] = -1;
		}

		// TODO handle border case
		for( int y = 0; y < pixelToregion.height-1; y++ ) {
			int pixelIndex = y*pixelToregion.width;
			for( int x = 0; x < pixelToregion.width-1; x++ , pixelIndex++) {
				int a = pixelToregion.data[pixelIndex];
				int b = pixelToregion.data[pixelIndex+1]; // pixel +1 x
				int c = pixelToregion.data[pixelIndex+pixelToregion.width]; // pixel +1 y

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
	}