@Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj instanceof Country) {
      return code.equals(((Country) obj).code);
    }
    return false;
  }