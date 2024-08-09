    @Test
    public void test_QuadKeyToTileXY() {
        testPoint(0, 1, TileSystem.QuadKeyToTileXY("2", null));
        testPoint(3, 1, TileSystem.QuadKeyToTileXY("13", null));
        testPoint(3, 5, TileSystem.QuadKeyToTileXY("213", null));

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
            testPoint(0, 0, TileSystem.QuadKeyToTileXY(zero, null));
            testPoint(maxTile, 0, TileSystem.QuadKeyToTileXY(one, null));
            testPoint(0, maxTile, TileSystem.QuadKeyToTileXY(two, null));
            testPoint(maxTile, maxTile, TileSystem.QuadKeyToTileXY(three, null));
        }
    }