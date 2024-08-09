@Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (String prefix : LIST) {
      s.append(prefix).append(";");
    }
    return s.toString();
  }