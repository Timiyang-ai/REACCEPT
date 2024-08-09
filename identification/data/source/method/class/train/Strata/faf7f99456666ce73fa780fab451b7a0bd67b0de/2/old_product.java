@Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj instanceof Currency) {
      return code.equals(((Currency) obj).code);
    }
    return false;
  }