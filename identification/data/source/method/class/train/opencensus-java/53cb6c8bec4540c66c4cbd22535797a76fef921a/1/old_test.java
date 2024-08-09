  @Test(expected = NullPointerException.class)
  public void toBinaryValue_NullSpanContext() {
    binaryFormat.toBinaryValue(null);
  }