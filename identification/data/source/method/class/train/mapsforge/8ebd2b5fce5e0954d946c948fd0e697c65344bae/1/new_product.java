public BoundingBox getBoundingBox() {
		if (this.boundingBox == null) {
			double minLatitude = Math.max(MercatorProjection.LATITUDE_MIN, MercatorProjection.tileYToLatitude(tileY + 1, zoomLevel));
			double minLongitude = Math.max(-180, MercatorProjection.tileXToLongitude(this.tileX, zoomLevel));
			double maxLatitude = Math.min(MercatorProjection.LATITUDE_MAX, MercatorProjection.tileYToLatitude(this.tileY, zoomLevel));
			double maxLongitude = Math.min(180, MercatorProjection.tileXToLongitude(tileX + 1, zoomLevel));
			if (maxLongitude == -180) {
				// fix for dateline crossing, where the right tile starts at -180 and causes an invalid bbox
				maxLongitude = 180;
			}
			this.boundingBox = new BoundingBox(minLatitude, minLongitude, maxLatitude, maxLongitude);
		}
		return this.boundingBox;
	}