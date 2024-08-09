public List<Inode> createPath(TachyonURI path, long blockSizeBytes, boolean recursive,
      boolean directory)
          throws FileAlreadyExistException, BlockInfoException, InvalidPathException {
    return createPath(path, blockSizeBytes, recursive, directory, System.currentTimeMillis());
  }