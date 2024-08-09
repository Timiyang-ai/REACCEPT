  @Test
  public void flatMap() {
    Map<String, String> expected = ImmutableMap.<String, String>builder()
        .put("one", "1")
        .put("1", "one")
        .put("two", "2")
        .put("2", "two")
        .put("three", "3")
        .put("3", "three")
        .put("four", "4")
        .put("4", "four")
        .build();

    Map<String, String> result = MapStream.of(map)
        .flatMap((k, v) -> Stream.of(Pair.of(k, Integer.toString(v)), Pair.of(Integer.toString(v), k)))
        .collect(pairsToImmutableMap());

    assertThat(result).isEqualTo(expected);
  }