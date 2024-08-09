private void seekWithCachingIncompleteBlocks(long pos) throws IOException {
    boolean isInCurrentBlock = pos / mBlockSize == mPos / mBlockSize;
    if (mShouldCacheCurrentBlock) {
      // Cache the current block or read forward to pos.
      readCurrentBlockTill(pos > mPos ? pos : mFileLength);
    }
    if (mPos == pos) {
      return;
    }

    if (isInCurrentBlock) {
      seekBlockInStream(pos);
      mCurrentBlockInStream.seek(mPos % mBlockSize);
    } else {
      seekBlockInStream(pos / mBlockSize * mBlockSize);
      checkAndAdvanceBlockInStream();
      readCurrentBlockTill(pos);
    }
  }