@Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (String prefix : mInnerList) {
      s.append(prefix).append(";");
    }
    return s.toString();
  }