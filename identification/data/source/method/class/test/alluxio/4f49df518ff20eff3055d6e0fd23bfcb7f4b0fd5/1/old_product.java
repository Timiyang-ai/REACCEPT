public int getDepth() {
    String path = mUri.getPath();
    if (path.isEmpty()) {
      return 0;
    }
    if (hasWindowsDrive(path, true)) {
      path = path.substring(3);
    }

    int depth = 0;

    int slash = path.length() == 1 && path.charAt(0) == '/' ? -1 : 0;
    while (slash != -1) {
      depth++;
      slash = path.indexOf(SEPARATOR, slash + 1);
    }
    return depth;
  }