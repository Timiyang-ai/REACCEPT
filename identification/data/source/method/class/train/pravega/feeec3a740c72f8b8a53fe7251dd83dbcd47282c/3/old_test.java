@Test
    public void testWrite() throws Exception {
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
            val w1 = b.write(new ByteArrayInputStream(toWrite, 0, size1), size1, true);
            Assert.assertEquals("Unexpected buffer id for w1.firstBlockAddress.", BUFFER_ID, LAYOUT.getBufferId(w1.getFirstBlockAddress()));
            Assert.assertEquals("Unexpected block id for w1.firstBlockAddress.", 1, LAYOUT.getBlockId(w1.getFirstBlockAddress()));
            int expectedRemaining1 = size1 > maxUsableSize ? size1 - maxUsableSize : 0;
            Assert.assertEquals("Unexpected w1.remainingLength", expectedRemaining1, w1.getRemainingLength());
            if (expectedRemaining1 > 0) {
                Assert.assertFalse("Unexpected result from hasCapacity() when filling up.", b.hasCapacity());
            }

            // Perform a second write and verify it won't override the first one.
            boolean hasCapacityBeforeSecondWrite = b.hasCapacity();
            int remainingCapacity = (LAYOUT.blocksPerBuffer() - b.getUsedBlockCount()) * LAYOUT.blockSize();

            int size2 = Math.max(0, size1 - secondWriteOffset);
            val w2 = b.write(new ByteArrayInputStream(toWrite, secondWriteOffset, size2), size2, false);
            int expectedRemaining2 = 0;
            if (expectedRemaining1 == 0 && hasCapacityBeforeSecondWrite) {
                // We have nothing remaining and still have capacity to write more.
                Assert.assertEquals("Unexpected buffer id for w2.firstBlockAddress.", BUFFER_ID, LAYOUT.getBufferId(w2.getFirstBlockAddress()));
                Assert.assertEquals("Unexpected block id for w2.firstBlockAddress.",
                        LAYOUT.getBlockId(w1.getLastBlockAddress()) + 1, LAYOUT.getBlockId(w2.getFirstBlockAddress()));
                expectedRemaining2 = size2 > remainingCapacity ? size2 - remainingCapacity : 0;
                Assert.assertEquals("Unexpected w2.remainingLength", expectedRemaining2, w2.getRemainingLength());
            } else {
                Assert.assertNull("Unexpected result when trying to write to a full buffer.", w2);
            }

            // Verify we can retrieve the data, then delete it. We will reuse the buffer in the next iteration.
            checkData(b, LAYOUT.getBlockId(w1.getFirstBlockAddress()), new ByteArraySegment(toWrite, 0, size1 - expectedRemaining1));
            if (w2 != null) {
                checkData(b, LAYOUT.getBlockId(w2.getFirstBlockAddress()), new ByteArraySegment(toWrite, secondWriteOffset, size2 - expectedRemaining2));
                b.delete(LAYOUT.getBlockId(w2.getFirstBlockAddress()));
            }
            b.delete(LAYOUT.getBlockId(w1.getFirstBlockAddress()));
        }
    }