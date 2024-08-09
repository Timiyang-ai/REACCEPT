public static void changeLocalFilePermission(String filePath, String perms) throws IOException {
    // TODO(cc): Switch to java's Files.setPosixFilePermissions() when Java 6 support is dropped.
    ShellUtils.execCommand(ShellUtils.getSetPermissionCommand(perms, filePath));
  }