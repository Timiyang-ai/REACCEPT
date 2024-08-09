public void moveCenter(double moveHorizontal, double moveVertical) {
		synchronized (this) {
			double pixelX = MercatorProjection.longitudeToPixelX(this.longitude, this.zoomLevel) - moveHorizontal;
			double pixelY = MercatorProjection.latitudeToPixelY(this.latitude, this.zoomLevel) - moveVertical;

			long mapSize = MercatorProjection.getMapSize(this.zoomLevel);
			pixelX = Math.min(Math.max(0, pixelX), mapSize);
			pixelY = Math.min(Math.max(0, pixelY), mapSize);

			double newLatitude = MercatorProjection.pixelYToLatitude(pixelY, this.zoomLevel);
			double newLongitude = MercatorProjection.pixelXToLongitude(pixelX, this.zoomLevel);
			setCenterInternal(new LatLong(newLatitude, newLongitude));
		}
		notifyObservers();
	}