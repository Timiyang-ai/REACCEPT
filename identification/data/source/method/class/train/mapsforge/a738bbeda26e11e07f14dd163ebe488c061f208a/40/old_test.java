    @Test
    public void latitudeToTileYTest() {
        for (byte zoomLevel = ZOOM_LEVEL_MIN; zoomLevel <= ZOOM_LEVEL_MAX; ++zoomLevel) {
            long tileY = MercatorProjection.latitudeToTileY(MercatorProjection.LATITUDE_MAX, zoomLevel);
            Assert.assertEquals(0, tileY);
            tileY = MercatorProjection.latitudeToTileYWithScaleFactor(MercatorProjection.LATITUDE_MAX, MercatorProjection.zoomLevelToScaleFactor(zoomLevel));
            Assert.assertEquals(0, tileY);

            tileY = MercatorProjection.latitudeToTileY(MercatorProjection.LATITUDE_MIN, zoomLevel);
            Assert.assertEquals(Tile.getMaxTileNumber(zoomLevel), tileY);
            tileY = MercatorProjection.latitudeToTileYWithScaleFactor(MercatorProjection.LATITUDE_MIN, MercatorProjection.zoomLevelToScaleFactor(zoomLevel));
            Assert.assertEquals(Tile.getMaxTileNumber(zoomLevel), tileY);
        }
    }