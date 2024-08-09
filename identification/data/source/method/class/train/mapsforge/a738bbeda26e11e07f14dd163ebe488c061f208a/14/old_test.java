    @Test
    public void pixelXToTileXTest() {
        for (int tileSize : TILE_SIZES) {
            for (byte zoomLevel = ZOOM_LEVEL_MIN; zoomLevel <= ZOOM_LEVEL_MAX; ++zoomLevel) {
                Assert.assertEquals(0, MercatorProjection.pixelXToTileX(0, zoomLevel, tileSize));
                Assert.assertEquals(0, MercatorProjection.pixelXToTileXWithScaleFactor(0, MercatorProjection.zoomLevelToScaleFactor(zoomLevel), tileSize));
            }
        }
    }