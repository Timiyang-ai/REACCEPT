@Test
  public void append() throws Exception {
    ByteBuffer buf = BufferUtils.getIncreasingByteBuffer((int) TEST_BLOCK_SIZE);
    Assert.assertEquals(TEST_BLOCK_SIZE, mWriter.append(buf));
    Assert.assertEquals(TEST_BLOCK_SIZE, mWriter.append(buf));
    mWriter.close();
    Assert.assertEquals(2 * TEST_BLOCK_SIZE, new File(mTestFilePath).length());
    // TODO(bin): Read data and assert it is really what we expected using
    // equalIncreasingByteBuffer.
  }