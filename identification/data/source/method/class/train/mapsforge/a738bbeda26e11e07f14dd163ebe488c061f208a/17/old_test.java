    @Test
    public void getMapSizeTest() {
        for (int tileSize : TILE_SIZES) {
            for (byte zoomLevel = ZOOM_LEVEL_MIN; zoomLevel <= ZOOM_LEVEL_MAX; ++zoomLevel) {
                long factor = Math.round(MercatorProjection.zoomLevelToScaleFactor(zoomLevel));
                Assert.assertEquals(tileSize * factor, MercatorProjection.getMapSize(zoomLevel, tileSize));
                Assert.assertEquals(MercatorProjection.getMapSizeWithScaleFactor(MercatorProjection.zoomLevelToScaleFactor(zoomLevel), tileSize),
                        MercatorProjection.getMapSize(zoomLevel, tileSize));
            }
            verifyInvalidGetMapSize((byte) -1, tileSize);
        }
    }