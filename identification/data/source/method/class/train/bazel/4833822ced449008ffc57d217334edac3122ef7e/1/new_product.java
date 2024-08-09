public static Location fromFile(Path path) {
    return fromFileAndOffsets(path.asFragment(), 0, 0);
  }