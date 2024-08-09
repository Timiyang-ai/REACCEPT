    @Test
    public void getBoundingBoxTest() {

        for (byte zoom = (byte) 0; zoom < 25; zoom++) {

            Tile tile1 = new Tile(0, 0, zoom, TILE_SIZE);
            if (zoom == 0) {
                Assert.assertTrue(tile1.getBoundingBox().equals(new BoundingBox(MercatorProjection.LATITUDE_MIN,
                        -180, MercatorProjection.LATITUDE_MAX, 180)));
            }
            Tile tile2 = new Tile(0, 0, zoom, TILE_SIZE);
            Assert.assertEquals(tile1.getBoundingBox().maxLatitude, tile2.getBoundingBox().maxLatitude, 0.0001);
            Assert.assertEquals(tile1.getBoundingBox().minLongitude, tile2.getBoundingBox().minLongitude, 0.0001);

            if (zoom >= 1) {
                Tile tile3 = new Tile(1, 1, zoom, TILE_SIZE);
                Assert.assertEquals(tile1.getBelow().getBoundingBox().minLatitude, tile3.getBoundingBox().minLatitude, 0.0001);
                Assert.assertEquals(tile1.getRight().getBoundingBox().minLongitude, tile3.getBoundingBox().minLongitude, 0.0001);
                if (zoom == 1) {
                    Assert.assertEquals(tile3.getBoundingBox().minLongitude, 0, 0.0001);
                    Assert.assertEquals(tile3.getBoundingBox().maxLongitude, 180, 0.0001);
                }
                Assert.assertEquals(tile3.getBoundingBox(), Tile.getBoundingBox(tile3, tile3));
            }

            Tile tile4 = new Tile(0, 0, zoom, TILE_SIZE);
            Assert.assertEquals(tile1.getBoundingBox().maxLatitude, tile4.getBoundingBox().maxLatitude, 0.0001);
            Assert.assertEquals(tile1.getBoundingBox().minLongitude, tile4.getBoundingBox().minLongitude, 0.0001);

            Tile tile5 = new Tile(0, 0, zoom, TILE_SIZE);
            Assert.assertEquals(tile1.getBoundingBox().maxLatitude, tile5.getBoundingBox().maxLatitude, 0.0001);
            Assert.assertEquals(tile1.getBoundingBox().minLongitude, tile5.getBoundingBox().minLongitude, 0.0001);

            Assert.assertEquals(tile1.getBoundingBox(), Tile.getBoundingBox(tile1, tile1));
            Assert.assertEquals(tile2.getBoundingBox(), Tile.getBoundingBox(tile2, tile2));
            Assert.assertEquals(tile4.getBoundingBox(), Tile.getBoundingBox(tile4, tile4));
            Assert.assertEquals(tile4.getBoundingBox(), Tile.getBoundingBox(tile5, tile5));

        }
    }