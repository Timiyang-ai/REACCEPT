    @Test
    public void test_PixelXYToLatLong() {
        final int pixelX = 45;
        final int pixelY = 45;
        final int levelOfDetail = 8;
        final double delta = 1E-3;

        final GeoPoint point = tileSystem.getGeoFromMercator(pixelX, pixelY, TileSystem.MapSize((double)levelOfDetail), null, true, true);

        Assert.assertEquals(-179.752807617187, point.getLongitude(), delta);
        Assert.assertEquals(85.0297584051224, point.getLatitude(), delta);
    }