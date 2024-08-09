@Test
  public void appendTest() throws IOException, StoreException {
    // buffer append
    doAppendTest(new Appender() {
      @Override
      public void append(LogSegment segment, ByteBuffer buffer) throws StoreException {
        int writeSize = buffer.remaining();
        int written = segment.appendFrom(buffer);
        assertEquals("Size written did not match size of buffer provided", writeSize, written);
      }
    });

    // channel append
    doAppendTest(new Appender() {
      @Override
      public void append(LogSegment segment, ByteBuffer buffer) throws StoreException {
        int writeSize = buffer.remaining();
        segment.appendFrom(Channels.newChannel(new ByteBufferInputStream(buffer)), writeSize);
        assertFalse("The buffer was not completely written", buffer.hasRemaining());
      }
    });

    // direct IO append
    if (Utils.isLinux()) {
      doAppendTest(new Appender() {
        @Override
        public void append(LogSegment segment, ByteBuffer buffer) throws StoreException {
          int writeSize = buffer.remaining();
          segment.appendFromDirectly(buffer.array(), 0, writeSize);
        }
      });
    }
  }