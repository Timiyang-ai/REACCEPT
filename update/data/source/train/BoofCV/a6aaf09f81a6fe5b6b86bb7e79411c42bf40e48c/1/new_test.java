@Test
	public void setDescription_partial() {
		// now tell it to set a description near the edge
		// only the first layer should be set
		PyramidKltFeature feature = new PyramidKltFeature(pyramid.getNumLayers(),featureReadius);
		feature.setPosition(featureReadius,featureReadius);
		tracker.setImage(pyramid,derivX,derivY);
		tracker.setDescription(feature);
		assertTrue( feature.desc[0].x != 0 );
		for( int i = 1; i < pyramid.getNumLayers(); i++ ) {
			assertTrue( feature.desc[i].Gxx == 0.0f );
		}
	}