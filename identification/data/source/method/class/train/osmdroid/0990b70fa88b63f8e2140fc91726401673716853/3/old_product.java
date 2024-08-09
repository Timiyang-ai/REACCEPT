public static Collection<Long> getTilesCoverage(final ArrayList<GeoPoint> pGeoPoints,
                                                    final int pZoomLevel) {
        final Set<Long> result = new HashSet<>();

        GeoPoint prevPoint = null;
        Point tile, prevTile = null;

        final int mapTileUpperBound = 1 << pZoomLevel;
        for (GeoPoint geoPoint : pGeoPoints) {

            final double d = TileSystem.GroundResolution(geoPoint.getLatitude(), pZoomLevel);

            if (result.size() != 0) {

                if (prevPoint != null) {

                    final double leadCoef = (geoPoint.getLatitude() - prevPoint.getLatitude()) / (geoPoint.getLongitude() - prevPoint.getLongitude());
                    final double brng;
                    if (geoPoint.getLongitude() > prevPoint.getLongitude()) {
                        brng = Math.PI / 2 - Math.atan(leadCoef);
                    } else {
                        brng = 3 * Math.PI / 2 - Math.atan(leadCoef);
                    }

                    final GeoPoint wayPoint = new GeoPoint(prevPoint.getLatitude(), prevPoint.getLongitude());

                    while ((((geoPoint.getLatitude() > prevPoint.getLatitude()) && (wayPoint.getLatitude() < geoPoint.getLatitude())) ||
                            (geoPoint.getLatitude() < prevPoint.getLatitude()) && (wayPoint.getLatitude() > geoPoint.getLatitude())) &&
                            (((geoPoint.getLongitude() > prevPoint.getLongitude()) && (wayPoint.getLongitude() < geoPoint.getLongitude())) ||
                                    ((geoPoint.getLongitude() < prevPoint.getLongitude()) && (wayPoint.getLongitude() > geoPoint.getLongitude())))) {

                        final double prevLatRad = wayPoint.getLatitude() * Math.PI / 180.0;
                        final double prevLonRad = wayPoint.getLongitude() * Math.PI / 180.0;

                        final double latRad = Math.asin(Math.sin(prevLatRad) * Math.cos(d / GeoConstants.RADIUS_EARTH_METERS) + Math.cos(prevLatRad) * Math.sin(d / GeoConstants.RADIUS_EARTH_METERS) * Math.cos(brng));
                        final double lonRad = prevLonRad + Math.atan2(Math.sin(brng) * Math.sin(d / GeoConstants.RADIUS_EARTH_METERS) * Math.cos(prevLatRad), Math.cos(d / GeoConstants.RADIUS_EARTH_METERS) - Math.sin(prevLatRad) * Math.sin(latRad));

                        wayPoint.setLatitude(((latRad * 180.0 / Math.PI)));
                        wayPoint.setLongitude(((lonRad * 180.0 / Math.PI)));

                        tile = getMapTileFromCoordinates(wayPoint.getLatitude(), wayPoint.getLongitude(), pZoomLevel);

                        if (!tile.equals(prevTile)) {
//Log.d(Constants.APP_TAG, "New Tile lat " + tile.x + " lon " + tile.y);
                            int ofsx = tile.x >= 0 ? 0 : -tile.x;
                            int ofsy = tile.y >= 0 ? 0 : -tile.y;
                            for (int xAround = tile.x + ofsx; xAround <= tile.x + 1 + ofsx; xAround++) {
                                for (int yAround = tile.y + ofsy; yAround <= tile.y + 1 + ofsy; yAround++) {
                                    final int tileY = MyMath.mod(yAround, mapTileUpperBound);
                                    final int tileX = MyMath.mod(xAround, mapTileUpperBound);
                                    result.add(MapTileIndex.getTileIndex(pZoomLevel, tileX, tileY));
                                }
                            }

                            prevTile = tile;
                        }
                    }
                }

            } else {
                tile = getMapTileFromCoordinates(geoPoint.getLatitude(), geoPoint.getLongitude(), pZoomLevel);
                prevTile = tile;

                int ofsx = tile.x >= 0 ? 0 : -tile.x;
                int ofsy = tile.y >= 0 ? 0 : -tile.y;
                for (int xAround = tile.x + ofsx; xAround <= tile.x + 1 + ofsx; xAround++) {
                    for (int yAround = tile.y + ofsy; yAround <= tile.y + 1 + ofsy; yAround++) {
                        final int tileY = MyMath.mod(yAround, mapTileUpperBound);
                        final int tileX = MyMath.mod(xAround, mapTileUpperBound);
                        result.add(MapTileIndex.getTileIndex(pZoomLevel, tileX, tileY));
                    }
                }
            }

            prevPoint = geoPoint;
        }
        return result;
    }