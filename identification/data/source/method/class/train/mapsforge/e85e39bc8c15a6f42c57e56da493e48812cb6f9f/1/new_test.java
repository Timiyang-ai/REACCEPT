    @Test
    public void setCapacityTest() {
        for (int tileSize : TILE_SIZES) {
            InMemoryTileCache inMemoryTileCache = new InMemoryTileCache(0);
            Assert.assertEquals(0, inMemoryTileCache.getCapacity());

            Tile tile1 = new Tile(1, 1, (byte) 1, tileSize);
            TileSource tileSource = OpenStreetMapMapnik.INSTANCE;
            Job job1 = new DownloadJob(tile1, tileSource);

            TileBitmap bitmap1 = GRAPHIC_FACTORY.createTileBitmap(tileSize, true);
            inMemoryTileCache.put(job1, bitmap1);
            Assert.assertFalse(inMemoryTileCache.containsKey(job1));

            inMemoryTileCache.setCapacity(1);
            Assert.assertEquals(1, inMemoryTileCache.getCapacity());

            inMemoryTileCache.put(job1, bitmap1);
            Assert.assertTrue(inMemoryTileCache.containsKey(job1));

            Tile tile2 = new Tile(2, 2, (byte) 2, tileSize);
            Job job2 = new DownloadJob(tile2, tileSource);

            inMemoryTileCache.put(job2, GRAPHIC_FACTORY.createTileBitmap(tileSize, true));
            Assert.assertFalse(inMemoryTileCache.containsKey(job1));
            Assert.assertTrue(inMemoryTileCache.containsKey(job2));

            verifyInvalidCapacity(inMemoryTileCache, -1);
        }
    }