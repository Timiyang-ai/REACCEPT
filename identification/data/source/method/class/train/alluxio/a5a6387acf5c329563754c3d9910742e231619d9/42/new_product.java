public CreatePathResult createPath(TachyonURI path, long blockSizeBytes, boolean recursive,
      boolean directory, long ttl, PermissionStatus ps)
          throws FileAlreadyExistsException, BlockInfoException, InvalidPathException {
    return createPath(path, blockSizeBytes, recursive, directory,
        System.currentTimeMillis(), ttl, ps);
  }