    @Test
    public void cacheSmallBlock() {
        SmallBlock smallBlock = new SmallBlock();
        BlockHeader header = new BlockHeader();
        NulsDigestData hash = NulsDigestData.calcDigestData("abcdefg".getBytes());
        header.setHash(hash);
        manager.cacheSmallBlock(smallBlock);
        assertTrue(true);

        this.getSmallBlock(hash, smallBlock);

        this.removeSmallBlock(hash);

        manager.cacheSmallBlock(smallBlock);

        this.clear();
    }