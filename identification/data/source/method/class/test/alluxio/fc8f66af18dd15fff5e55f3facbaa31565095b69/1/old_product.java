public static String concatPath(Object base, Object... paths) throws IllegalArgumentException {
    Preconditions.checkArgument(base != null, "Failed to concatPath: base is null");
    Preconditions.checkArgument(paths != null, "Failed to concatPath: a null set of paths");
    List<String> trimmedPathList = new ArrayList<>();
    String trimmedBase =
        CharMatcher.is(AlluxioURI.SEPARATOR.charAt(0)).trimTrailingFrom(base.toString());
    trimmedPathList.add(trimmedBase);
    for (Object path : paths) {
      if (path == null) {
        continue;
      }
      String trimmedPath =
          CharMatcher.is(AlluxioURI.SEPARATOR.charAt(0)).trimFrom(path.toString());
      if (!trimmedPath.isEmpty()) {
        trimmedPathList.add(trimmedPath);
      }
    }
    if (trimmedPathList.size() == 1 && trimmedBase.isEmpty()) {
      // base must be "[/]+"
      return AlluxioURI.SEPARATOR;
    }
    return Joiner.on(AlluxioURI.SEPARATOR).join(trimmedPathList);

  }