  @Test(dataProvider = "sketch", expectedExceptions = IllegalArgumentException.class)
  public void ensureCapacity_negative(FrequencySketch<Integer> sketch) {
    sketch.ensureCapacity(-1);
  }