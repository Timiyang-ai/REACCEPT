@Test
  public void close() throws Exception {
    ByteBuffer buf = BufferUtils.getIncreasingByteBuffer((int) TEST_BLOCK_SIZE);
    Assert.assertEquals(TEST_BLOCK_SIZE, mWriter.append(buf));
    mWriter.close();
    // Append after close, expect append to fail and throw ClosedChannelException
    mThrown.expect(FailedPreconditionException.class);
    mWriter.append(buf);
  }