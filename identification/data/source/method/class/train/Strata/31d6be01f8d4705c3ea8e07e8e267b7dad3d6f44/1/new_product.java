@Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    StandardId other = (StandardId) obj;
    return scheme.equals(other.scheme) && value.equals(other.value);
  }