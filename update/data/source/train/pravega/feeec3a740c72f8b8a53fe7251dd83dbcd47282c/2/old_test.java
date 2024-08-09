@Test
    public void testNewBlockMetadata() {
        val blockIds = getAllOneBitNumbers(getBlockIdBitCount());
        val lengths = getAllOneBitNumbers(getLengthBitCount());
        val addresses = getAllOneBitNumbers(getAddressBitCount());
        val firsts = Arrays.asList(true, false);
        for (boolean first : firsts) {
            for (long b : blockIds) {
                int expectedBlockId = (int) b;
                for (long l : lengths) {
                    int expectedLength = (int) l;
                    for (long a : addresses) {
                        int expectedAddress = (int) a;
                        long m = layout().newBlockMetadata(first, expectedBlockId, expectedLength, expectedAddress);
                        Assert.assertTrue(layout().isUsedBlock(m));
                        Assert.assertEquals(first, layout().isFirstBlock(m));
                        Assert.assertEquals(expectedBlockId, layout().getNextFreeBlockId(m));
                        Assert.assertEquals(expectedLength, layout().getLength(m));
                        Assert.assertEquals(expectedAddress, layout().getSuccessorAddress(m));
                    }
                }
            }
        }

        // Test the empty metadata.
        Assert.assertFalse(layout().isFirstBlock(layout().emptyBlockMetadata()));
        Assert.assertFalse(layout().isUsedBlock(layout().emptyBlockMetadata()));
        Assert.assertEquals(CacheLayout.NO_BLOCK_ID, layout().getNextFreeBlockId(layout().emptyBlockMetadata()));
        Assert.assertEquals(0, layout().getLength(layout().emptyBlockMetadata()));
        Assert.assertEquals(CacheLayout.NO_ADDRESS, layout().getSuccessorAddress(layout().emptyBlockMetadata()));
    }