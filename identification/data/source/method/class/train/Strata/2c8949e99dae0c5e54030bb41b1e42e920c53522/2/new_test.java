  @Test
  public void test_zipWithIndex() {
    Stream<String> base = Stream.of("a", "b", "c");
    List<ObjIntPair<String>> test = Guavate.zipWithIndex(base).collect(Collectors.toList());
    assertThat(test).isEqualTo(ImmutableList.of(ObjIntPair.of("a", 0), ObjIntPair.of("b", 1), ObjIntPair.of("c", 2)));
  }