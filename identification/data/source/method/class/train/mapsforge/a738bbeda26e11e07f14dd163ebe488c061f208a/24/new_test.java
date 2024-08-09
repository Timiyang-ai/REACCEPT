    @Test
    public void longitudeToPixelXTest() {
        for (int tileSize : TILE_SIZES) {
            for (byte zoomLevel = ZOOM_LEVEL_MIN; zoomLevel <= ZOOM_LEVEL_MAX; ++zoomLevel) {
                long mapSize = MercatorProjection.getMapSize(zoomLevel, tileSize);
                double pixelX = MercatorProjection.longitudeToPixelX(LatLongUtils.LONGITUDE_MIN, mapSize);
                Assert.assertEquals(0, pixelX, 0);
                pixelX = MercatorProjection.longitudeToPixelXWithScaleFactor(LatLongUtils.LONGITUDE_MIN, MercatorProjection.zoomLevelToScaleFactor(zoomLevel), tileSize);
                Assert.assertEquals(0, pixelX, 0);

                pixelX = MercatorProjection.longitudeToPixelX(0, mapSize);
                Assert.assertEquals((float) mapSize / 2, pixelX, 0);
                mapSize = MercatorProjection.getMapSizeWithScaleFactor(MercatorProjection.zoomLevelToScaleFactor(zoomLevel), tileSize);
                pixelX = MercatorProjection.longitudeToPixelXWithScaleFactor(0, MercatorProjection.zoomLevelToScaleFactor(zoomLevel), tileSize);
                Assert.assertEquals((float) mapSize / 2, pixelX, 0);

                pixelX = MercatorProjection.longitudeToPixelX(LatLongUtils.LONGITUDE_MAX, mapSize);
                Assert.assertEquals(mapSize, pixelX, 0);
                pixelX = MercatorProjection.longitudeToPixelXWithScaleFactor(LatLongUtils.LONGITUDE_MAX, MercatorProjection.zoomLevelToScaleFactor(zoomLevel), tileSize);
                Assert.assertEquals(mapSize, pixelX, 0);
            }
        }
    }