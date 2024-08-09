public long createUfsFile(AlluxioURI ufsUri) throws FileAlreadyExistsException, IOException {
    return mUnderFileSystemManager.createFile(ufsUri);
  }