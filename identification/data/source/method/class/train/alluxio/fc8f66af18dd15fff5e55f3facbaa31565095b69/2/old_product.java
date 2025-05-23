public static String concatPath(Object base, Object... paths) throws IllegalArgumentException {
    Preconditions.checkArgument(base != null, "Failed to concatPath: base is null");
    Preconditions.checkArgument(paths != null, "Failed to concatPath: a null set of paths");
    List<String> trimmedPathList = new ArrayList<String>();
    String trimmedBase =
        CharMatcher.is(TachyonURI.SEPARATOR.charAt(0)).trimTrailingFrom(base.toString().trim());
    trimmedPathList.add(trimmedBase);
    for (Object path : paths) {
      if (null == path) {
        continue;
      }
      String trimmedPath =
          CharMatcher.is(TachyonURI.SEPARATOR.charAt(0)).trimFrom(path.toString().trim());
      if (!trimmedPath.isEmpty()) {
        trimmedPathList.add(trimmedPath);
      }
    }
    if (trimmedPathList.size() == 1 && trimmedBase.isEmpty()) {
      // base must be "[/]+"
      return TachyonURI.SEPARATOR;
    }
    return Joiner.on(TachyonURI.SEPARATOR).join(trimmedPathList);

  }