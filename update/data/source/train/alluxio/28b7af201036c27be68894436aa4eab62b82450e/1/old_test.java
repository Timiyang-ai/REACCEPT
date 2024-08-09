@Test
  public void close() throws Exception {
    mThrown.expect(ClosedChannelException.class);

    ByteBuffer buf = BufferUtils.getIncreasingByteBuffer((int) TEST_BLOCK_SIZE);
    Assert.assertEquals(TEST_BLOCK_SIZE, mWriter.append(buf));
    mWriter.close();
    // Append after close, expect append to fail and throw ClosedChannelException
    mWriter.append(buf);
  }