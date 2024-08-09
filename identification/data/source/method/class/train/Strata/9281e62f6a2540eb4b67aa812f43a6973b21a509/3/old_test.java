  @Test
  public void toListMultimap() {
    Map<String, Integer> map = ImmutableMap.of("a", 1, "aa", 2, "b", 10, "bb", 20, "c", 1);
    ListMultimap<String, Integer> expected = ImmutableListMultimap.of("a", 1, "a", 2, "b", 10, "b", 20, "c", 1);
    ListMultimap<String, Integer> result = MapStream.of(map).mapKeys(s -> s.substring(0, 1)).toListMultimap();
    assertThat(result).isEqualTo(expected);
  }