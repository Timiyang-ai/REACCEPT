    @Test
    public void test_LatLongToPixelXY() {
        final PointL point = tileSystem.getMercatorFromGeo(60, 60, TileSystem.MapSize((double)10), null, true);
        Assert.assertEquals(174762, point.x);
        Assert.assertEquals(76126, point.y);
    }