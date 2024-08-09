public static byte zoomForBounds(Dimension dimension, BoundingBox boundingBox, int tileSize) {
		long mapSize = MercatorProjection.getMapSize((byte) 0, tileSize);
		double pixelXMax = MercatorProjection.longitudeToPixelX(boundingBox.maxLongitude, mapSize);
		double pixelXMin = MercatorProjection.longitudeToPixelX(boundingBox.minLongitude, mapSize);
		double zoomX = -Math.log(Math.abs(pixelXMax - pixelXMin) / dimension.width) / Math.log(2);
		double pixelYMax = MercatorProjection.latitudeToPixelY(boundingBox.maxLatitude, mapSize);
		double pixelYMin = MercatorProjection.latitudeToPixelY(boundingBox.minLatitude, mapSize);
		double zoomY = -Math.log(Math.abs(pixelYMax - pixelYMin) / dimension.height) / Math.log(2);
		double zoom = Math.floor(Math.min(zoomX, zoomY));
		if (zoom > Byte.MAX_VALUE)
			return Byte.MAX_VALUE;
		else if (zoom < 0)
			return 0;
		else
			return (byte) zoom;
	}