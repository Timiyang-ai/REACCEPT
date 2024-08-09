public BoundingBox getBoundingBox() {
		if (this.boundingBox == null) {
			double minLatitude = MercatorProjection.tileYToLatitude(this.getBelow().tileY, zoomLevel);
			double minLongitude = MercatorProjection.tileXToLongitude(this.tileX, zoomLevel);
			double maxLatitude = MercatorProjection.tileYToLatitude(this.tileY, zoomLevel);
			double maxLongitude = MercatorProjection.tileXToLongitude(this.getRight().tileX, zoomLevel);
			if (maxLongitude == -180) {
				// fix for dateline crossing, where the right tile starts at -180 and causes an invalid bbox
				maxLongitude = 180;
			}
			this.boundingBox = new BoundingBox(minLatitude, minLongitude, maxLatitude, maxLongitude);
		}
		return this.boundingBox;
	}