    private void getSmallBlock(NulsDigestData hash, SmallBlock smallBlock) {
        SmallBlock sb = manager.getSmallBlockByHash(NulsDigestData.calcDigestData("abcdefg".getBytes()));
        assertEquals(sb.getHeader().getHash(), smallBlock.getHeader().getHash());
    }