public GeoPoint getCenterPoint() {
		double latitudeOffset = (this.maxLatitude - this.minLatitude) / 2;
		double longitudeOffset = (this.maxLongitude - this.minLongitude) / 2;
		return new GeoPoint(this.minLatitude + latitudeOffset, this.minLongitude + longitudeOffset);
	}