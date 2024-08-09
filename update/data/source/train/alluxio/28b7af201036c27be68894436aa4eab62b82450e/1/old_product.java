@Override
  public void close() throws IOException {
    if (mClosed) {
      return;
    }

    try {
      // This aborts the block if the block is not fully read.
      updateBlockWriter(mBlockMeta.getBlockSize());

      Closer closer = Closer.create();
      if (mBlockWriter != null) {
        closer.register(mBlockWriter);
      }
      if (mUnderFileSystemInputStream != null) {
        closer.register(mUnderFileSystemInputStream);
      }
      closer.close();
    } finally {
      mClosed = true;
    }
  }