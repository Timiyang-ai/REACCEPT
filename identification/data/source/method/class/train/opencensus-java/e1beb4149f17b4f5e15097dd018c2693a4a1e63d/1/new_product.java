@Internal
  public long getLowerLong() {
    return (idHi < 0) ? -idHi : idHi;
  }