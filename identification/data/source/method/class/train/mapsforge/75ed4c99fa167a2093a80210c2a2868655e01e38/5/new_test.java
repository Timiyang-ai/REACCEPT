    @Test
    public void zoomForBoundsTest() {
        // TODO rewrite this unit tests to make it easier to understand
        Dimension[] dimensions = {new Dimension(200, 300), new Dimension(500, 400), new Dimension(1000, 600),
                new Dimension(3280, 1780), new Dimension(100, 200), new Dimension(500, 200)};
        BoundingBox[] boundingBoxes = {new BoundingBox(12.2, 0, 34.3, 120), new BoundingBox(-30, 20, 30, 30),
                new BoundingBox(20.3, 100, 30.4, 120), new BoundingBox(4.4, 2, 4.5, 2.2),
                new BoundingBox(50.43, 12.23, 50.44, 12.24), new BoundingBox(50.43, 12, 50.44, 40)};
        int[] tileSizes = {256, 512, 500, 620, 451};

        byte[] results = {1, 0, 0, 0, 0, 2, 1, 1, 1, 1, 3, 2, 2, 2, 2, 10, 9, 9, 9, 9, 14, 13, 13, 13, 13, 3, 2,
                2, 2, 2, 2, 1, 1, 1, 1, 3, 2, 2, 1, 2, 5, 4, 4, 3, 4, 11, 10, 10, 10, 10, 15, 14, 14, 13, 14, 4, 3, 3,
                3, 3, 3, 2, 2, 2, 2, 3, 2, 2, 2, 2, 6, 5, 5, 4, 5, 12, 11, 11, 11, 11, 15, 14, 14, 14, 14, 5, 4, 4, 4,
                4, 5, 4, 4, 3, 4, 5, 4, 4, 4, 4, 7, 6, 6, 6, 6, 14, 13, 13, 13, 13, 17, 16, 16, 16, 16, 7, 6, 6, 6,
                6, 0, 0, 0, 0, 0, 2, 1, 1, 0, 1, 2, 1, 1, 1, 1, 9, 8, 8, 8, 8, 13, 12, 12, 12, 12, 2, 1, 1, 1, 1,
                2, 1, 1, 1, 1, 2, 1, 1, 0, 1, 4, 3, 3, 3, 3, 11, 10, 10, 10, 10, 14, 13, 13, 12, 13, 4, 3, 3, 3, 3};

        int i = 0;
        for (Dimension dimension : dimensions) {
            for (BoundingBox boundingBox : boundingBoxes) {
                for (int tileSize : tileSizes) {
                    Assert.assertEquals(results[i], LatLongUtils.zoomForBounds(dimension, boundingBox, tileSize));
                    ++i;
                }
            }
        }
    }