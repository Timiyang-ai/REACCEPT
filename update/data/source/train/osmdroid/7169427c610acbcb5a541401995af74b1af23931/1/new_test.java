@Test
    public void testGetLongitudeFromX01() {
        final int iterations = 10;
        for (int i = 0 ; i <= iterations ; i ++) {
            final double longitude = tileSystem.getMinLongitude() + i * (tileSystem.getMaxLongitude() - tileSystem.getMinLongitude()) / iterations;
            checkLongitude(longitude, tileSystem.getLongitudeFromX01(((double)i) / iterations, true));
        }
        checkLongitude(tileSystem.getMinLongitude(), tileSystem.getLongitudeFromX01(0, true));
        checkLongitude(0, tileSystem.getLongitudeFromX01(0.5, true));
        checkLongitude(tileSystem.getMaxLongitude(), tileSystem.getLongitudeFromX01(1, true));
    }