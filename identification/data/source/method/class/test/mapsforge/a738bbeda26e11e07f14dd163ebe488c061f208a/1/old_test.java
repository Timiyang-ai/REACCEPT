    @Test
    public void pixelYToLatitudeTest() {
        for (int tileSize : TILE_SIZES) {
            for (byte zoomLevel = ZOOM_LEVEL_MIN; zoomLevel <= ZOOM_LEVEL_MAX; ++zoomLevel) {
                long mapSize = MercatorProjection.getMapSize(zoomLevel, tileSize);
                double latitude = MercatorProjection.pixelYToLatitude(0, mapSize);
                Assert.assertEquals(MercatorProjection.LATITUDE_MAX, latitude, 0);
                latitude = MercatorProjection.pixelYToLatitudeWithScaleFactor(0, MercatorProjection.zoomLevelToScaleFactor(zoomLevel), tileSize);
                Assert.assertEquals(MercatorProjection.LATITUDE_MAX, latitude, 0);

                latitude = MercatorProjection.pixelYToLatitude((float) mapSize / 2, mapSize);
                Assert.assertEquals(0, latitude, 0);
                mapSize = MercatorProjection.getMapSizeWithScaleFactor(MercatorProjection.zoomLevelToScaleFactor(zoomLevel), tileSize);
                latitude = MercatorProjection.pixelYToLatitudeWithScaleFactor((float) mapSize / 2, MercatorProjection.zoomLevelToScaleFactor(zoomLevel), tileSize);
                Assert.assertEquals(0, latitude, 0);

                latitude = MercatorProjection.pixelYToLatitude(mapSize, mapSize);
                Assert.assertEquals(MercatorProjection.LATITUDE_MIN, latitude, 0);
                latitude = MercatorProjection.pixelYToLatitudeWithScaleFactor(mapSize, MercatorProjection.zoomLevelToScaleFactor(zoomLevel), tileSize);
                Assert.assertEquals(MercatorProjection.LATITUDE_MIN, latitude, 0);
            }

            verifyInvalidPixelYToLatitude(-1, (byte) 0, tileSize);
            verifyInvalidPixelYToLatitude(tileSize + 1, (byte) 0, tileSize);
        }
    }