  @Test(dataProvider = "sketch")
  public void indexOf_aroundZero(FrequencySketch<Integer> sketch) {
    Set<Integer> indexes = new HashSet<>(16);
    int[] hashes = { -1, 0, 1 };
    for (int hash : hashes) {
      for (int i = 0; i < 4; i++) {
        indexes.add(sketch.indexOf(hash, i));
      }
    }
    assertThat(indexes, hasSize(4 * hashes.length));
  }