@Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj instanceof StandardId) {
      StandardId other = (StandardId) obj;
      return scheme.equals(other.scheme) && value.equals(other.value);
    }
    return false;
  }