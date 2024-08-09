    @Test
    public void getParentTest() {
        Tile rootTile = new Tile(0, 0, (byte) 0, TILE_SIZE);
        Assert.assertNull(rootTile.getParent());

        Assert.assertEquals(rootTile, new Tile(0, 0, (byte) 1, TILE_SIZE).getParent());
        Assert.assertEquals(rootTile, new Tile(1, 0, (byte) 1, TILE_SIZE).getParent());
        Assert.assertEquals(rootTile, new Tile(0, 1, (byte) 1, TILE_SIZE).getParent());
        Assert.assertEquals(rootTile, new Tile(1, 1, (byte) 1, TILE_SIZE).getParent());
    }