public static void changeLocalFilePermission(String filePath, String perms) throws IOException {
    Files.setPosixFilePermissions(Paths.get(filePath), PosixFilePermissions.fromString(perms));
  }