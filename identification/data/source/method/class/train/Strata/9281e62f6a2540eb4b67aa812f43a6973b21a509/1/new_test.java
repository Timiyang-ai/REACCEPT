  @Test
  public void keys() {
    List<String> result = MapStream.of(map).keys().collect(toImmutableList());
    assertThat(result).isEqualTo(ImmutableList.of("one", "two", "three", "four"));
  }