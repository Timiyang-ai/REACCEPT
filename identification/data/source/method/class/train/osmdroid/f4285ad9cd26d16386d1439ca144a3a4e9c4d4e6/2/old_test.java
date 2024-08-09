    @Test
    public void test_MapSize() {
        for (int zoomLevel = mMinZoomLevel ; zoomLevel <= mMaxZoomLevel ; zoomLevel ++) {
            Assert.assertEquals(256L << zoomLevel, (long)TileSystem.MapSize((double)zoomLevel));
        }
    }