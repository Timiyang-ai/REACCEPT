@Test
    public void testWrite() {
        final byte[] toWrite = new byte[LAYOUT.bufferSize() + 3 * LAYOUT.blockSize()];
        rnd.nextBytes(toWrite);
        final int sizeIncrement = 2 * LAYOUT.blockSize() + LAYOUT.blockSize() / 2;
        final int secondWriteOffset = 1;
        final int maxUsableSize = LAYOUT.bufferSize() - LAYOUT.blockSize(); // Exclude metadata.

        // We reuse the buffer multiple times. We want to also verify there is no leak between each run.
        @Cleanup
        val b = newBuffer();
        for (int size1 = 0; size1 < toWrite.length; size1 += sizeIncrement) {
            Assert.assertEquals("Expected buffer to be clean.", 1, b.getUsedBlockCount());
            val w1 = b.write(new ByteArraySegment(toWrite, 0, size1), CacheLayout.NO_ADDRESS);
            Assert.assertEquals("Unexpected value for w1.firstBlockId.", 1, w1.getFirstBlockId());
            int expectedWritten1 = Math.min(size1, maxUsableSize);
            Assert.assertEquals("Unexpected w1.writtenLength", expectedWritten1, w1.getWrittenLength());
            if (expectedWritten1 == maxUsableSize) {
                Assert.assertFalse("Unexpected result from hasCapacity() when filling up.", b.hasCapacity());
            }

            // Perform a second write and verify it won't override the first one.
            boolean hasCapacityBeforeSecondWrite = b.hasCapacity();
            int remainingCapacity = (LAYOUT.blocksPerBuffer() - b.getUsedBlockCount()) * LAYOUT.blockSize();

            int size2 = Math.max(0, size1 - secondWriteOffset);
            val w2 = b.write(new ByteArraySegment(toWrite, secondWriteOffset, size2), CacheLayout.NO_ADDRESS);
            int expectedWritten2 = 0;
            if (expectedWritten1 < maxUsableSize && hasCapacityBeforeSecondWrite) {
                // We have nothing remaining and still have capacity to write more.
                Assert.assertEquals("Unexpected value for w2.firstBlockId.",
                        LAYOUT.getBlockId(w1.getLastBlockAddress()) + 1, w2.getFirstBlockId());
                expectedWritten2 = Math.min(size2, remainingCapacity);
                Assert.assertEquals("Unexpected w2.writtenLength", expectedWritten2, w2.getWrittenLength());
            } else {
                Assert.assertNull("Unexpected result when trying to write to a full buffer.", w2);
            }

            // Verify we can retrieve the data, then delete it. We will reuse the buffer in the next iteration.
            checkData(b, LAYOUT.getBlockId(w1.getLastBlockAddress()), new ByteArraySegment(toWrite, 0, expectedWritten1));
            if (w2 != null) {
                checkData(b, LAYOUT.getBlockId(w2.getLastBlockAddress()), new ByteArraySegment(toWrite, secondWriteOffset, expectedWritten2));
                b.delete(LAYOUT.getBlockId(w2.getLastBlockAddress()));
            }
            b.delete(LAYOUT.getBlockId(w1.getLastBlockAddress()));
        }
    }