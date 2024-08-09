    @Test
    public void tileXToLongitudeTest() {
        for (int tileSize : TILE_SIZES) {
            for (byte zoomLevel = ZOOM_LEVEL_MIN; zoomLevel <= ZOOM_LEVEL_MAX; ++zoomLevel) {
                double longitude = MercatorProjection.tileXToLongitude(0, zoomLevel);
                Assert.assertEquals(LatLongUtils.LONGITUDE_MIN, longitude, 0);
                longitude = MercatorProjection.tileXToLongitudeWithScaleFactor(0, MercatorProjection.zoomLevelToScaleFactor(zoomLevel));
                Assert.assertEquals(LatLongUtils.LONGITUDE_MIN, longitude, 0);

                long tileX = MercatorProjection.getMapSize(zoomLevel, tileSize) / tileSize;
                longitude = MercatorProjection.tileXToLongitude(tileX, zoomLevel);
                Assert.assertEquals(LatLongUtils.LONGITUDE_MAX, longitude, 0);
                tileX = MercatorProjection.getMapSizeWithScaleFactor(MercatorProjection.zoomLevelToScaleFactor(zoomLevel), tileSize) / tileSize;
                longitude = MercatorProjection.tileXToLongitudeWithScaleFactor(tileX, MercatorProjection.zoomLevelToScaleFactor(zoomLevel));
                Assert.assertEquals(LatLongUtils.LONGITUDE_MAX, longitude, 0);
            }
        }
    }