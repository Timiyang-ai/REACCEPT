@Test
  public void getFileSpanForMessageBadArgsTest() throws IOException {
    Log log = new Log(tempDir.getAbsolutePath(), LOG_CAPACITY, SEGMENT_CAPACITY, metrics);
    try {
      LogSegment firstSegment = log.getFirstSegment();
      log.setActiveSegment(firstSegment.getName());
      Offset endOffsetOfPrevMessage = new Offset(firstSegment.getName(), firstSegment.getEndOffset() + 1);
      try {
        log.getFileSpanForMessage(endOffsetOfPrevMessage, 1);
        fail("Should have failed because endOffsetOfPrevMessage > endOffset of log segment");
      } catch (IllegalArgumentException e) {
        // expected. Nothing to do.
      }
      // write a single byte into the log
      endOffsetOfPrevMessage = log.getStartOffset();
      CHANNEL_APPENDER.append(log, ByteBuffer.allocate(1));
      try {
        // provide the wrong size
        log.getFileSpanForMessage(endOffsetOfPrevMessage, 2);
        fail("Should have failed because endOffsetOfPrevMessage + size > endOffset of log segment");
      } catch (IllegalStateException e) {
        // expected. Nothing to do.
      }
    } finally {
      log.close();
      cleanDirectory(tempDir);
    }
  }