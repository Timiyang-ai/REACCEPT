public static boolean isAncestor(@NotNull File ancestor, @NotNull File file, boolean strict) {
    File parent = strict ? getParentFile(file) : file;
    while (true) {
      if (parent == null) {
        return false;
      }
      // Do not user file.equals as it incorrectly works on MacOS
      if (pathsEqual(parent.getPath(), ancestor.getPath())) {
        return true;
      }
      parent = getParentFile(parent);
    }
  }