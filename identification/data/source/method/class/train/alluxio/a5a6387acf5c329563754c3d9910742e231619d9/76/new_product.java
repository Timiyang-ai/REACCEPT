public CreatePathResult createPath(TachyonURI path, long blockSizeBytes, boolean recursive,
      boolean directory, PermissionStatus ps)
          throws FileAlreadyExistsException, BlockInfoException, InvalidPathException {
    return createPath(path, blockSizeBytes, recursive, directory, System.currentTimeMillis(),
        Constants.NO_TTL, ps);
  }