@Override
  @ToString
  public String toString() {
    if (amount == (long) amount) {
      return currency + " " + Long.toString((long) amount);
    }
    return currency + " " + Double.toString(amount);
  }