public synchronized long reinitializeFile(String path, long blockSizeBytes, long ttl)
      throws InvalidPathException, FileDoesNotExistException {
    long fileId = mFileSystemMaster.getFileId(new TachyonURI(path));
    FileInfo fileInfo = mFileSystemMaster.getFileInfo(fileId);
    if (!fileInfo.isCompleted() || mFileSystemMaster.getLostFiles().contains(fileId)) {
      LOG.info("Recreate the file {} with block size of {} bytes", path, blockSizeBytes);
      return mFileSystemMaster.reinitializeFile(new TachyonURI(path), blockSizeBytes, ttl);
    }
    return -1;
  }