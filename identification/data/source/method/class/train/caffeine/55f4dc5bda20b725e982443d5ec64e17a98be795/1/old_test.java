  @Test(dataProvider = "sketch")
  public void increment_once(FrequencySketch<Integer> sketch) {
    sketch.increment(item);
    assertThat(sketch.frequency(item), is(1));
  }