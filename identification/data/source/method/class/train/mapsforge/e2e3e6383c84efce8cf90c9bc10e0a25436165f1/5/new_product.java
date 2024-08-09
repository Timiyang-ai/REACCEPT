public LatLong getCenterPoint() {
		double latitudeOffset = (this.maxLatitude - this.minLatitude) / 2;
		double longitudeOffset = (this.maxLongitude - this.minLongitude) / 2;
		return new LatLong(this.minLatitude + latitudeOffset, this.minLongitude + longitudeOffset, true);
	}