  @Test
  public void filter() {
    Map<String, Integer> expected = ImmutableMap.of("one", 1, "two", 2);
    Map<String, Integer> result = MapStream.of(map).filter((k, v) -> k.equals("one") || v == 2).toMap();
    assertThat(result).isEqualTo(expected);
  }