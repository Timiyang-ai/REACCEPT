public static BasicPoint projectGeoPoint(final GeoPoint aGeoPoint, final int aZoom, final BasicPoint aUseAsReturnValue) {
		final BasicPoint p = (aUseAsReturnValue != null) ? aUseAsReturnValue : new BasicPoint();
		
		final double aLon = aGeoPoint.getLongitudeE6()*1E-6;
		final double aLat = aGeoPoint.getLatitudeE6()*1E-6; 
		p.x = (int) Math.floor((aLon + 180) / 360 * (1 << aZoom));
		p.y = (int) Math.floor((1 - Math.log(Math.tan(aLat * DEG2RAD) + 1 / Math.cos(aLat * DEG2RAD)) / Math.PI) / 2 * (1 << aZoom));
		
		return p;
	}