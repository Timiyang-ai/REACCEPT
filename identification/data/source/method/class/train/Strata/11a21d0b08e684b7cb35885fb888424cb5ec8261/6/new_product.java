@Override
  @ToString
  public String toString() {
    return currency + " " +
        (DoubleMath.isMathematicalInteger(amount) ? Long.toString((long) amount) : Double.toString(amount));
  }