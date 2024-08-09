@Test
    public void testGetLatitudeFromY01() {
        checkLatitude(TileSystem.MaxLatitude, TileSystem.getLatitudeFromY01(0, true));
        checkLatitude(0, TileSystem.getLatitudeFromY01(0.5, true));
        checkLatitude(TileSystem.MinLatitude, TileSystem.getLatitudeFromY01(1, true));
    }