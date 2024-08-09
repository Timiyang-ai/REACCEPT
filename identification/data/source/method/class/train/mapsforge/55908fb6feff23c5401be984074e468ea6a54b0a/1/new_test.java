    @Test
    public void getMaxTileNumberTest() {
        Assert.assertEquals(0, Tile.getMaxTileNumber((byte) 0));
        Assert.assertEquals(1, Tile.getMaxTileNumber((byte) 1));
        Assert.assertEquals(3, Tile.getMaxTileNumber((byte) 2));
        Assert.assertEquals(7, Tile.getMaxTileNumber((byte) 3));
        Assert.assertEquals(1023, Tile.getMaxTileNumber((byte) 10));
        Assert.assertEquals(1048575, Tile.getMaxTileNumber((byte) 20));
        Assert.assertEquals(1073741823, Tile.getMaxTileNumber((byte) 30));

        verifyInvalidMaxTileNumber((byte) -1);
        verifyInvalidMaxTileNumber(Byte.MIN_VALUE);
    }