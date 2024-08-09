@Test
    public void cacheSmallBlock() {
        SmallBlock smallBlock = new SmallBlock();
        BlockHeader header = new BlockHeader();
        NulsDigestData hash = NulsDigestData.calcDigestData("abcdefg".getBytes());
        header.setHash(hash);
        smallBlock.setHeader(header);
        NulsDigestData requestHash = NulsDigestData.calcDigestData(new byte[]{0});
        manager.cacheSmallBlock(requestHash, smallBlock);
        assertTrue(true);

        this.getSmallBlock(hash, smallBlock);

        this.removeSmallBlock(hash);

        manager.cacheSmallBlock(requestHash, smallBlock);

        this.clear();
    }