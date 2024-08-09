public static boolean isAncestor(File ancestor, File file, boolean strict) throws IOException {
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