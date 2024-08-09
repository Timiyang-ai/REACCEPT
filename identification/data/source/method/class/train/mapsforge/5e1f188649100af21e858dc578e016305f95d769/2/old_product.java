public static double latitudeToPixelY(double latitude, byte zoomLevel) {
		double sinLatitude = Math.sin(latitude * (Math.PI / 180));
		long mapSize = getMapSize(zoomLevel);
		return (0.5 - Math.log((1 + sinLatitude) / (1 - sinLatitude)) / (4 * Math.PI)) * mapSize;
	}