public long createUfsFile(String ufsPath) throws FileAlreadyExistsException, IOException {
    return mUnderFileSystemManager.createFile(ufsPath);
  }