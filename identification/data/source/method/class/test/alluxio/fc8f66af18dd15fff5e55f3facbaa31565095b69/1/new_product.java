public static String concatPath(Object base, Object... paths) throws IllegalArgumentException {
    Preconditions.checkArgument(base != null, "Failed to concatPath: base is null");
    Preconditions.checkArgument(paths != null, "Failed to concatPath: a null set of paths");
    String trimmedBase = SEPARATOR_MATCHER.trimTrailingFrom(base.toString());

    StringBuilder output = new StringBuilder();
    output.append(trimmedBase);
    for (Object path : paths) {
      if (path == null) {
        continue;
      }
      String trimmedPath = SEPARATOR_MATCHER.trimFrom(path.toString());
      if (!trimmedPath.isEmpty()) {
        output.append(AlluxioURI.SEPARATOR);
        output.append(trimmedPath);
      }
    }
    if (output.length() == 0) {
      // base must be "[/]+"
      return AlluxioURI.SEPARATOR;
    }
    return output.toString();
  }