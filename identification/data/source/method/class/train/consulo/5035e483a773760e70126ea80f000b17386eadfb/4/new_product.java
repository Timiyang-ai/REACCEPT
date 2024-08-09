public static boolean isAncestor(@NotNull File ancestor, @NotNull File file, boolean strict) {
    File parent = strict ? getParentFile(file) : file;
    while (true) {
      if (parent == null) {
        return false;
      }
      if (parent.equals(ancestor)) {
        return true;
      }
      parent = getParentFile(parent);
    }
  }