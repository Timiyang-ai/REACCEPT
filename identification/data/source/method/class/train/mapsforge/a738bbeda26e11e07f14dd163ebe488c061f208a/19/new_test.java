    @Test
    public void pixelXToLongitudeTest() {
        for (int tileSize : TILE_SIZES) {
            for (byte zoomLevel = ZOOM_LEVEL_MIN; zoomLevel <= ZOOM_LEVEL_MAX; ++zoomLevel) {
                long mapSize = MercatorProjection.getMapSize(zoomLevel, tileSize);
                double longitude = MercatorProjection.pixelXToLongitude(0, mapSize);
                Assert.assertEquals(LatLongUtils.LONGITUDE_MIN, longitude, 0);
                longitude = MercatorProjection.pixelXToLongitudeWithScaleFactor(0, MercatorProjection.zoomLevelToScaleFactor(zoomLevel), tileSize);
                Assert.assertEquals(LatLongUtils.LONGITUDE_MIN, longitude, 0);

                longitude = MercatorProjection.pixelXToLongitude((float) mapSize / 2, mapSize);
                Assert.assertEquals(0, longitude, 0);
                mapSize = MercatorProjection.getMapSizeWithScaleFactor(MercatorProjection.zoomLevelToScaleFactor(zoomLevel), tileSize);
                longitude = MercatorProjection.pixelXToLongitudeWithScaleFactor((float) mapSize / 2, MercatorProjection.zoomLevelToScaleFactor(zoomLevel), tileSize);
                Assert.assertEquals(0, longitude, 0);

                longitude = MercatorProjection.pixelXToLongitude(mapSize, mapSize);
                Assert.assertEquals(LatLongUtils.LONGITUDE_MAX, longitude, 0);
                longitude = MercatorProjection.pixelXToLongitudeWithScaleFactor(mapSize, MercatorProjection.zoomLevelToScaleFactor(zoomLevel), tileSize);
                Assert.assertEquals(LatLongUtils.LONGITUDE_MAX, longitude, 0);
            }

            verifyInvalidPixelXToLongitude(-1, (byte) 0, tileSize);
            verifyInvalidPixelXToLongitude(tileSize + 1, (byte) 0, tileSize);
        }
    }