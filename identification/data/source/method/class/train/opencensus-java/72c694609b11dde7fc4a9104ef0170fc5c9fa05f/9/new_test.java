  @Test
  public void fromMillis_TooLow() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("'seconds' is less than minimum (-315576000000): -315576000001");
    Duration.fromMillis(-315576000001000L);
  }