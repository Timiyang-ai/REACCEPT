@Override
  public void close() throws IOException {
    if (mClosed) {
      return;
    }

    try {
      // This aborts the block if the block is not fully read.
      updateBlockWriter(mBlockMeta.getBlockSize());

      if (mUnderFileSystemInputStream != null) {
        UnderFileInputStreamsManager.INSTANCE.checkIn(mUnderFileSystemInputStream);
        mUnderFileSystemInputStream = null;
      }

      if(mBlockWriter!=null) {
        mBlockWriter.close();
      }

    } finally {
      mClosed = true;
    }
  }