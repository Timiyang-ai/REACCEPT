    @Test
    public void test_TileXYToQuadKey() {
        Assert.assertEquals("2", TileSystem.TileXYToQuadKey(0,1, 1));
        Assert.assertEquals("13", TileSystem.TileXYToQuadKey(3 ,1, 2));
        Assert.assertEquals("213", TileSystem.TileXYToQuadKey(3 ,5, 3));
        String zero = "";
        String one = "";
        String two = "";
        String three = "";
        for (int zoom = 1 ; zoom <= TileSystem.getMaximumZoomLevel() ; zoom ++) {
            zero += "0";
            one += "1";
            two += "2";
            three += "3";
            final int maxTile = (1 << zoom) - 1;
            Assert.assertEquals(zero, TileSystem.TileXYToQuadKey(0 ,0, zoom));
            Assert.assertEquals(one, TileSystem.TileXYToQuadKey(maxTile, 0, zoom));
            Assert.assertEquals(two, TileSystem.TileXYToQuadKey(0, maxTile, zoom));
            Assert.assertEquals(three, TileSystem.TileXYToQuadKey(maxTile, maxTile, zoom));
        }
    }