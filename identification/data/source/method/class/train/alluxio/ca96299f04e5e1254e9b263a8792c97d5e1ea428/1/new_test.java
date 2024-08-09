@Test
  public void append() throws Exception {
    ByteBuffer buf = BufferUtils.getIncreasingByteBuffer(TEST_BLOCK_SIZE);
    Assert.assertEquals(TEST_BLOCK_SIZE, mWriter.append(buf));
    Assert.assertEquals(TEST_BLOCK_SIZE, mWriter.append(buf));
    mWriter.close();
    Assert.assertEquals(2 * TEST_BLOCK_SIZE, new File(mTestFilePath).length());
    ByteBuffer result = ByteBuffer.wrap(Files.readAllBytes(Paths.get(mTestFilePath)));
    result.position(0).limit(TEST_BLOCK_SIZE);
    BufferUtils.equalIncreasingByteBuffer(0, TEST_BLOCK_SIZE, result.slice());
    result.position(TEST_BLOCK_SIZE).limit(TEST_BLOCK_SIZE * 2);
    BufferUtils.equalIncreasingByteBuffer(0, TEST_BLOCK_SIZE, result.slice());
  }