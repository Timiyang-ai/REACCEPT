public static double latitudeToPixelY(double latitude, long mapSize) {
		double sinLatitude = Math.sin(latitude * (Math.PI / 180));
		// FIXME improve this formula so that it works correctly without the clipping
		double pixelY = (0.5 - Math.log((1 + sinLatitude) / (1 - sinLatitude)) / (4 * Math.PI)) * mapSize;
		return Math.min(Math.max(0, pixelY), mapSize);
	}