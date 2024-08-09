    @Test
    public void longitudeToTileXTest() {
        for (byte zoomLevel = ZOOM_LEVEL_MIN; zoomLevel <= ZOOM_LEVEL_MAX; ++zoomLevel) {
            long tileX = MercatorProjection.longitudeToTileX(LatLongUtils.LONGITUDE_MIN, zoomLevel);
            Assert.assertEquals(0, tileX);
            tileX = MercatorProjection.longitudeToTileXWithScaleFactor(LatLongUtils.LONGITUDE_MIN, MercatorProjection.zoomLevelToScaleFactor(zoomLevel));
            Assert.assertEquals(0, tileX);

            tileX = MercatorProjection.longitudeToTileX(LatLongUtils.LONGITUDE_MAX, zoomLevel);
            Assert.assertEquals(Tile.getMaxTileNumber(zoomLevel), tileX);
            tileX = MercatorProjection.longitudeToTileXWithScaleFactor(LatLongUtils.LONGITUDE_MAX, MercatorProjection.zoomLevelToScaleFactor(zoomLevel));
            Assert.assertEquals(Tile.getMaxTileNumber(zoomLevel), tileX);
        }
    }