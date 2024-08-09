public boolean setDescription(PyramidKltFeature feature) {
		boolean valid = false;
		for (int layer = 0; layer < image.getNumLayers(); layer++) {
			float scale = (float)image.getScale(layer);
			float x = feature.x / scale;
			float y = feature.y /  scale;

			setupKltTracker(layer);

			feature.desc[layer].setPosition(x, y);
			if( !tracker.setDescription(feature.desc[layer]) )
				break;
			feature.maxLayer = layer;
			valid = true;
		}
		return valid;
	}