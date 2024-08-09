  @Test
  public void values() {
    List<Integer> result = MapStream.of(map).values().collect(toImmutableList());
    assertThat(result).isEqualTo(ImmutableList.of(1, 2, 3, 4));
  }