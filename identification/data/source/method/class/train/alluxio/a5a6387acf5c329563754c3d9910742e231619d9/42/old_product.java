public CreatePathResult createPath(TachyonURI path, long blockSizeBytes, boolean recursive,
      boolean directory, long ttl)
          throws FileAlreadyExistsException, BlockInfoException, InvalidPathException {
    return createPath(path, blockSizeBytes, recursive, directory, System.currentTimeMillis(), ttl);
  }