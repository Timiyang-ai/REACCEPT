public static short getLocalFilePermission(String filePath) throws IOException {
    Set<PosixFilePermission> permission =
        Files.readAttributes(Paths.get(filePath), PosixFileAttributes.class).permissions();
    // Translate posix file permissions to short mode.
    int mode = 0;
    for (PosixFilePermission action : PosixFilePermission.values()) {
      mode = mode << 1;
      mode += permission.contains(action) ? 1 : 0;
    }
    return (short) mode;
  }