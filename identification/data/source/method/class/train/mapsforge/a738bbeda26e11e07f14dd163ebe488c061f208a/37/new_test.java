    @Test
    public void tileYToLatitudeTest() {
        for (int tileSize : TILE_SIZES) {
            for (byte zoomLevel = ZOOM_LEVEL_MIN; zoomLevel <= ZOOM_LEVEL_MAX; ++zoomLevel) {
                double latitude = MercatorProjection.tileYToLatitude(0, zoomLevel);
                Assert.assertEquals(MercatorProjection.LATITUDE_MAX, latitude, 0);
                latitude = MercatorProjection.tileYToLatitudeWithScaleFactor(0, MercatorProjection.zoomLevelToScaleFactor(zoomLevel));
                Assert.assertEquals(MercatorProjection.LATITUDE_MAX, latitude, 0);

                long tileY = MercatorProjection.getMapSize(zoomLevel, tileSize) / tileSize;
                latitude = MercatorProjection.tileYToLatitude(tileY, zoomLevel);
                Assert.assertEquals(MercatorProjection.LATITUDE_MIN, latitude, 0);
                tileY = MercatorProjection.getMapSizeWithScaleFactor(MercatorProjection.zoomLevelToScaleFactor(zoomLevel), tileSize) / tileSize;
                latitude = MercatorProjection.tileYToLatitudeWithScaleFactor(tileY, MercatorProjection.zoomLevelToScaleFactor(zoomLevel));
                Assert.assertEquals(MercatorProjection.LATITUDE_MIN, latitude, 0);
            }
        }
    }