  @Test
  public void reset() {
    boolean reset = false;
    FrequencySketch<Integer> sketch = new FrequencySketch<>();
    sketch.ensureCapacity(64);

    for (int i = 1; i < 20 * sketch.table.length; i++) {
      sketch.increment(i);
      if (sketch.size != i) {
        reset = true;
        break;
      }
    }
    assertThat(reset, is(true));
    assertThat(sketch.size, lessThanOrEqualTo(sketch.sampleSize / 2));
  }