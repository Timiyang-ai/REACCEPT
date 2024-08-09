@Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj instanceof Currency) {
      return equals((Currency) obj);
    }
    return false;
  }