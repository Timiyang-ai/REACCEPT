public static short getLocalFileMode(String filePath) throws IOException {
    Set<PosixFilePermission> permission =
        Files.readAttributes(Paths.get(filePath), PosixFileAttributes.class).permissions();
    return translatePermissionMode(permission);
  }