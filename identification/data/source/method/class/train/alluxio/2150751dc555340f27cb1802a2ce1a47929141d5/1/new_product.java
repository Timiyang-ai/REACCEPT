@Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (mUri.getScheme() != null) {
      sb.append(mUri.getScheme());
      sb.append("://");
    }
    if (hasAuthority()) {
      if (mUri.getScheme() == null) {
        sb.append("//");
      }
      sb.append(mUri.getAuthority().toString());
    }
    if (mUri.getPath() != null) {
      String path = mUri.getPath();
      if (path.indexOf('/') == 0 && hasWindowsDrive(path, true) && // has windows drive
          mUri.getScheme() == null && // but no scheme
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