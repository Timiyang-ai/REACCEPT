@Test
  public void getViewTest() throws IOException {
    Log log = new Log(tempDir.getAbsolutePath(), LOG_CAPACITY, SEGMENT_CAPACITY, metrics);
    try {
      long writeStartOffset = log.getEndOffset().getOffset();
      long writeSize = (int) (SEGMENT_CAPACITY - writeStartOffset);
      byte[] buf = TestUtils.getRandomBytes((int) (writeSize));
      log.appendFrom(ByteBuffer.wrap(buf));
      List<BlobReadOptions> list = new ArrayList<>();
      list.add(new BlobReadOptions(writeStartOffset, writeSize, -1, null));
      StoreMessageReadSet readSet = log.getView(list);
      ByteBufferChannel channel = new ByteBufferChannel(ByteBuffer.allocate(buf.length));
      readSet.writeTo(0, channel, 0, buf.length);
      assertArrayEquals("Data read does not match data written", buf, channel.getBuffer().array());
    } finally {
      log.close();
      cleanDirectory(tempDir);
    }
  }