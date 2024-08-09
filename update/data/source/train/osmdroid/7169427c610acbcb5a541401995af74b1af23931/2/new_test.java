@Test
    public void testGetLatitudeFromY01() {
        checkLatitude(tileSystem.getMaxLatitude(), tileSystem.getLatitudeFromY01(0, true));
        checkLatitude(0, tileSystem.getLatitudeFromY01(0.5, true));
        checkLatitude(tileSystem.getMinLatitude(), tileSystem.getLatitudeFromY01(1, true));
    }