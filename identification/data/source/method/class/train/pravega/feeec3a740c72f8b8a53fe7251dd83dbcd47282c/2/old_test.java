@Test
    public void testDelete() throws Exception {
        final byte[] toWrite = new byte[LAYOUT.bufferSize()];
        rnd.nextBytes(toWrite);
        final int multiBlockLength = LAYOUT.blockSize() + 1;

        @Cleanup
        val b = newBuffer();
        int expectedUsedBlockCount = 1;

        val empty = b.write(new ByteArrayInputStream(toWrite), 0, true);
        expectedUsedBlockCount += 1;
        val multiBlock = b.write(new ByteArrayInputStream(toWrite), multiBlockLength, true);
        expectedUsedBlockCount += 2;
        val multiBuf = b.write(new ByteArrayInputStream(toWrite), 2 * LAYOUT.blockSize(), true);
        expectedUsedBlockCount += 2;
        val successorAddress = LAYOUT.calculateAddress(BUFFER_ID + 1, 2);
        b.setSuccessor(multiBuf.getLastBlockAddress(), successorAddress);

        val emptyDelete = b.delete(LAYOUT.getBlockId(empty.getFirstBlockAddress()));
        Assert.assertEquals(0, emptyDelete.getDeletedLength());
        Assert.assertEquals(CacheLayout.NO_ADDRESS, emptyDelete.getSuccessorAddress());
        expectedUsedBlockCount -= 1;
        Assert.assertEquals(expectedUsedBlockCount, b.getUsedBlockCount());

        val multiBlockDelete = b.delete(LAYOUT.getBlockId(multiBlock.getFirstBlockAddress()));
        Assert.assertEquals(multiBlockLength, multiBlockDelete.getDeletedLength());
        Assert.assertEquals(CacheLayout.NO_ADDRESS, multiBlockDelete.getSuccessorAddress());
        expectedUsedBlockCount -= 2;
        Assert.assertEquals(expectedUsedBlockCount, b.getUsedBlockCount());

        val multiBufDelete = b.delete(LAYOUT.getBlockId(multiBuf.getFirstBlockAddress()));
        Assert.assertEquals(2 * LAYOUT.blockSize(), multiBufDelete.getDeletedLength());
        Assert.assertEquals(successorAddress, multiBufDelete.getSuccessorAddress());
        expectedUsedBlockCount -= 2;
        Assert.assertEquals(expectedUsedBlockCount, b.getUsedBlockCount());
    }