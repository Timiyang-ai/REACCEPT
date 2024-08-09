  @Test
  public void create_SecondsTooLow() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("'seconds' is less than minimum (-315576000000): -315576000001");
    Duration.create(-315576000001L, 0);
  }