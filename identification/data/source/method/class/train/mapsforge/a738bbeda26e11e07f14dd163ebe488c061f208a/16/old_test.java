    @Test
    public void tileToPixelTest() {
        for (int tileSize : TILE_SIZES) {
            Assert.assertEquals(0, MercatorProjection.tileToPixel(0, tileSize));
            Assert.assertEquals(tileSize, MercatorProjection.tileToPixel(1, tileSize));
            Assert.assertEquals(tileSize * 2, MercatorProjection.tileToPixel(2, tileSize));
        }
    }