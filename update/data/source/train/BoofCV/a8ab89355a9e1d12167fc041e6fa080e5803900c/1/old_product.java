public void process( SiftImageScaleSpace ss ) {
		// set up data structures
		foundPoints.reset();
		this.ss = ss;
		octavePixelOffset = 0;

		// extract features in each octave
		for( int octave = 0; octave < ss.actualOctaves; octave++ ) {
			// start processing at the second DOG since it needs the scales above and below
			int indexDOG = octave*(ss.numScales-1)+1;
			int indexScale = octave*ss.numScales+1;

			currentPixelScale = ss.pixelScale[octave];

			ss.storage.reshape( ss.scale[indexScale].width , ss.scale[indexScale].height );

			for( int scale = 1; scale < ss.numScales-2; scale++ , indexScale++,indexDOG++ ) {

				// use the scale-space image as input for derivatives
				derivXX.setImage(ss.scale[indexScale]);
				derivXY.setImage(ss.scale[indexScale]);
				derivYY.setImage(ss.scale[indexScale]);

				// the current scale factor being considered
				currentSigma = ss.computeScaleSigma(octave,scale);

				detectFeatures(indexDOG);
			}

			// when the images are sub-sampled between octaves the sampling starts at pixel 1 in (x,y)
			octavePixelOffset += currentPixelScale;
		}
	}