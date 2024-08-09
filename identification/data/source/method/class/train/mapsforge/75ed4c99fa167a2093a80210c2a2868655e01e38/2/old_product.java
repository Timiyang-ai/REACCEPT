public static byte zoomForBounds(Dimension dimension, BoundingBox boundingBox, int tileSize) {
        double dxMax = MercatorProjection.longitudeToPixelX(boundingBox.maxLongitude, (byte) 0, tileSize) / tileSize;
        double dxMin = MercatorProjection.longitudeToPixelX(boundingBox.minLongitude, (byte) 0, tileSize) / tileSize;
        double zoomX = Math.floor(-Math.log(3.8) * Math.log(Math.abs(dxMax - dxMin)) + dimension.width / tileSize);
        double dyMax = MercatorProjection.latitudeToPixelY(boundingBox.maxLatitude, (byte) 0, tileSize) / tileSize;
        double dyMin = MercatorProjection.latitudeToPixelY(boundingBox.minLatitude, (byte) 0, tileSize) / tileSize;
        double zoomY = Math.floor(-Math.log(3.8) * Math.log(Math.abs(dyMax - dyMin)) + dimension.height / tileSize);
        return (byte) Double.valueOf(Math.min(zoomX, zoomY)).intValue();
    }