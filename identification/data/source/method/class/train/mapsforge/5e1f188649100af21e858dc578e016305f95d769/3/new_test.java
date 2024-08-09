    @Test
    public void latitudeToPixelYTest() {
        for (int tileSize : TILE_SIZES) {
            for (byte zoomLevel = ZOOM_LEVEL_MIN; zoomLevel <= ZOOM_LEVEL_MAX; ++zoomLevel) {
                long mapSize = MercatorProjection.getMapSize(zoomLevel, tileSize);
                double pixelY = MercatorProjection.latitudeToPixelY(MercatorProjection.LATITUDE_MAX, mapSize);
                Assert.assertEquals(0, pixelY, 0);

                pixelY = MercatorProjection.latitudeToPixelYWithScaleFactor(MercatorProjection.LATITUDE_MAX, MercatorProjection.zoomLevelToScaleFactor(zoomLevel),
                        tileSize);
                Assert.assertEquals(0, pixelY, 0);

                pixelY = MercatorProjection.latitudeToPixelY(0, mapSize);
                Assert.assertEquals((float) mapSize / 2, pixelY, 0);
                pixelY = MercatorProjection.latitudeToPixelYWithScaleFactor(0, MercatorProjection.zoomLevelToScaleFactor(zoomLevel), tileSize);
                Assert.assertEquals((float) mapSize / 2, pixelY, 0);

                pixelY = MercatorProjection.latitudeToPixelY(MercatorProjection.LATITUDE_MIN, mapSize);
                Assert.assertEquals(mapSize, pixelY, 0);
                pixelY = MercatorProjection.latitudeToPixelYWithScaleFactor(MercatorProjection.LATITUDE_MIN, MercatorProjection.zoomLevelToScaleFactor(zoomLevel), tileSize);
                Assert.assertEquals(mapSize, pixelY, 0);
            }
        }
    }