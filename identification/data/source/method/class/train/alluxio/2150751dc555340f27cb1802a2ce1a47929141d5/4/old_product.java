@Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    String fullScheme = getFullScheme(mUri.getScheme());
    if (fullScheme != null) {
      sb.append(fullScheme);
      sb.append("://");
    }
    if (mUri.getAuthority() != null) {
      if (fullScheme == null) {
        sb.append("//");
      }
      sb.append(mUri.getAuthority());
    }
    if (mUri.getPath() != null) {
      String path = mUri.getPath();
      if (path.indexOf('/') == 0 && hasWindowsDrive(path, true) && // has windows drive
          fullScheme == null && // but no scheme
          mUri.getAuthority() == null) { // or authority
        path = path.substring(1); // remove slash before drive
      }
      sb.append(path);
    }
    if (mUri.getQuery() != null) {
      sb.append("?");
      sb.append(mUri.getQuery());
    }
    return sb.toString();
  }