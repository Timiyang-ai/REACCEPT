@Test
    public void testDelete() {
        final byte[] toWrite = new byte[LAYOUT.bufferSize()];
        rnd.nextBytes(toWrite);
        final int multiBlockLength = LAYOUT.blockSize() + 1;

        @Cleanup
        val b = newBuffer();
        int expectedUsedBlockCount = 1;

        val empty = b.write(new ByteArraySegment(toWrite, 0, 0), CacheLayout.NO_ADDRESS);
        expectedUsedBlockCount += 1;
        val multiBlock = b.write(new ByteArraySegment(toWrite, 0, multiBlockLength), CacheLayout.NO_ADDRESS);
        expectedUsedBlockCount += 2;
        val predecessorAddress = LAYOUT.calculateAddress(BUFFER_ID + 1, 2);
        val multiBuf = b.write(new ByteArraySegment(toWrite, 0, 2 * LAYOUT.blockSize()), predecessorAddress);
        expectedUsedBlockCount += 2;

        val emptyDelete = b.delete(LAYOUT.getBlockId(empty.getLastBlockAddress()));
        Assert.assertEquals(0, emptyDelete.getDeletedLength());
        Assert.assertEquals(CacheLayout.NO_ADDRESS, emptyDelete.getPredecessorAddress());
        expectedUsedBlockCount -= 1;
        Assert.assertEquals(expectedUsedBlockCount, b.getUsedBlockCount());

        val multiBlockDelete = b.delete(LAYOUT.getBlockId(multiBlock.getLastBlockAddress()));
        Assert.assertEquals(multiBlockLength, multiBlockDelete.getDeletedLength());
        Assert.assertEquals(CacheLayout.NO_ADDRESS, multiBlockDelete.getPredecessorAddress());
        expectedUsedBlockCount -= 2;
        Assert.assertEquals(expectedUsedBlockCount, b.getUsedBlockCount());

        val multiBufDelete = b.delete(LAYOUT.getBlockId(multiBuf.getLastBlockAddress()));
        Assert.assertEquals(2 * LAYOUT.blockSize(), multiBufDelete.getDeletedLength());
        Assert.assertEquals(predecessorAddress, multiBufDelete.getPredecessorAddress());
        expectedUsedBlockCount -= 2;
        Assert.assertEquals(expectedUsedBlockCount, b.getUsedBlockCount());
    }