@Test
    public void testGetLongitudeFromX01() {
        final int iterations = 10;
        for (int i = 0 ; i <= iterations ; i ++) {
            final double longitude = TileSystem.MinLongitude + i * (TileSystem.MaxLongitude - TileSystem.MinLongitude) / iterations;
            checkLongitude(longitude, TileSystem.getLongitudeFromX01(((double)i) / iterations, true));
        }
        checkLongitude(TileSystem.MinLongitude, TileSystem.getLongitudeFromX01(0, true));
        checkLongitude(0, TileSystem.getLongitudeFromX01(0.5, true));
        checkLongitude(TileSystem.MaxLongitude, TileSystem.getLongitudeFromX01(1, true));
    }