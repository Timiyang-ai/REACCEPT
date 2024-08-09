public boolean setDescription(PyramidKltFeature feature) {
		for (int layer = 0; layer < image.getNumLayers(); layer++) {
			float scale = (float)image.getScale(layer);
			float x = feature.x / scale;
			float y = feature.y /  scale;

			setupKltTracker(layer);

			feature.desc[layer].setPosition(x, y);
			if( !tracker.setDescription(feature.desc[layer]) )
				return false;
		}
		return true;
	}