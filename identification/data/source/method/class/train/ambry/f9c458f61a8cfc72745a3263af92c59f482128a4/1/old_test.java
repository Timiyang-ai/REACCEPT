@Test
  public void writeFromTest()
      throws IOException {
    String currSegmentName = "log_current";
    LogSegment segment = getSegment(currSegmentName, STANDARD_SEGMENT_SIZE);
    try {
      byte[] bufOne = TestUtils.getRandomBytes(STANDARD_SEGMENT_SIZE / 3);
      byte[] bufTwo = TestUtils.getRandomBytes(STANDARD_SEGMENT_SIZE / 2);

      segment.writeFrom(Channels.newChannel(new ByteBufferInputStream(ByteBuffer.wrap(bufOne))), 0, bufOne.length);
      assertEquals("End offset in current segment is not as expected", bufOne.length, segment.getEndOffset());
      readAndEnsureMatch(segment, 0, bufOne);

      // overwrite using bufTwo
      segment.writeFrom(Channels.newChannel(new ByteBufferInputStream(ByteBuffer.wrap(bufTwo))), 0, bufTwo.length);
      assertEquals("End offset in current segment is not as expected", bufTwo.length, segment.getEndOffset());
      readAndEnsureMatch(segment, 0, bufTwo);

      // overwrite using bufOne
      segment.writeFrom(Channels.newChannel(new ByteBufferInputStream(ByteBuffer.wrap(bufOne))), 0, bufOne.length);
      // end offset should not have changed
      assertEquals("End offset in current segment is not as expected", bufTwo.length, segment.getEndOffset());
      readAndEnsureMatch(segment, 0, bufOne);
      readAndEnsureMatch(segment, bufOne.length, Arrays.copyOfRange(bufTwo, bufOne.length, bufTwo.length));

      // write at random locations
      for (int i = 0; i < 10; i++) {
        long offset = Utils.getRandomLong(TestUtils.RANDOM, segment.getCapacityInBytes() - bufOne.length);
        segment
            .writeFrom(Channels.newChannel(new ByteBufferInputStream(ByteBuffer.wrap(bufOne))), offset, bufOne.length);
        readAndEnsureMatch(segment, offset, bufOne);
      }

      // try to overwrite using a channel that won't fit
      ByteBuffer failBuf = ByteBuffer.wrap(TestUtils.getRandomBytes(STANDARD_SEGMENT_SIZE + 1));
      long writeOverFlowCount = metrics.overflowWriteError.getCount();
      try {
        segment.writeFrom(Channels.newChannel(new ByteBufferInputStream(failBuf)), 0, failBuf.remaining());
        fail("WriteFrom should have failed because data won't fit");
      } catch (IndexOutOfBoundsException e) {
        assertEquals("Write overflow should have been reported", writeOverFlowCount + 1,
            metrics.overflowWriteError.getCount());
        assertEquals("Position of buffer has changed", 0, failBuf.position());
      }

      // data cannot be written at invalid offsets.
      long[] invalidOffsets = {-1, STANDARD_SEGMENT_SIZE, STANDARD_SEGMENT_SIZE + 1};
      ByteBuffer buffer = ByteBuffer.wrap(TestUtils.getRandomBytes(1));
      for (long invalidOffset : invalidOffsets) {
        try {
          segment.writeFrom(Channels.newChannel(new ByteBufferInputStream(buffer)), invalidOffset, buffer.remaining());
          fail("WriteFrom should have failed because offset provided for write is invalid");
        } catch (IndexOutOfBoundsException e) {
          assertEquals("Position of buffer has changed", 0, buffer.position());
        }
      }

      segment.close();
      // ensure that writeFrom fails.
      try {
        segment.writeFrom(Channels.newChannel(new ByteBufferInputStream(buffer)), 0, buffer.remaining());
        fail("WriteFrom should have failed because segments are closed");
      } catch (ClosedChannelException e) {
        assertEquals("Position of buffer has changed", 0, buffer.position());
      }
    } finally {
      closeSegmentAndDeleteFile(segment);
    }
  }