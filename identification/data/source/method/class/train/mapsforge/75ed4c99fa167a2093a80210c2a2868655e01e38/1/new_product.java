public static byte zoomForBounds(Dimension dimension, BoundingBox boundingBox, int tileSize) {
		long mapSize = MercatorProjection.getMapSize((byte) 0, tileSize);
		double dxMax = MercatorProjection.longitudeToPixelX(boundingBox.maxLongitude, mapSize) / tileSize;
		double dxMin = MercatorProjection.longitudeToPixelX(boundingBox.minLongitude, mapSize) / tileSize;
		double zoomX = Math.floor(-Math.log(3.8) * Math.log(Math.abs(dxMax - dxMin)) + (float) dimension.width
				/ tileSize);
		double dyMax = MercatorProjection.latitudeToPixelY(boundingBox.maxLatitude, mapSize) / tileSize;
		double dyMin = MercatorProjection.latitudeToPixelY(boundingBox.minLatitude, mapSize) / tileSize;
		double zoomY = Math.floor(-Math.log(3.8) * Math.log(Math.abs(dyMax - dyMin)) + (float) dimension.height
				/ tileSize);
		return (byte) Math.min(zoomX, zoomY);
	}