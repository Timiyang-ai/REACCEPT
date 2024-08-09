public static boolean isAncestor(@NotNull File ancestor, @NotNull File file, boolean strict) {
    return isAncestor(ancestor.getPath(), file.getPath(), strict);
  }