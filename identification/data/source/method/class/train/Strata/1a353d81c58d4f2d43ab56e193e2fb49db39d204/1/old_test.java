  @Test
  public void test_concatToList() {
    Iterable<String> iterable1 = Arrays.asList("a", "b", "c");
    Iterable<String> iterable2 = Arrays.asList("d", "e", "f");
    List<String> test = Guavate.concatToList(iterable1, iterable2);
    assertThat(test).isEqualTo(ImmutableList.of("a", "b", "c", "d", "e", "f"));
  }