@Nullable
  public String getLeadingPath(int n) {
    String path = mUri.getPath();
    if (n == 0 && path.indexOf(AlluxioURI.SEPARATOR) == 0) { // the special case
      return AlluxioURI.SEPARATOR;
    }
    int depth = getDepth();
    if (depth < n) {
      return null;
    } else if (depth == n) {
      return path;
    } else {
      String[] comp = path.split(SEPARATOR);
      return StringUtils.join(Arrays.asList(comp).subList(0, n + 1), SEPARATOR);
    }
  }