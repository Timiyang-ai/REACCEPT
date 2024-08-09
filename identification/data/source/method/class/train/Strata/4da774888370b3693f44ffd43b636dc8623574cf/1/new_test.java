  @Test
  public void test_stream_Iterable() {
    Iterable<String> iterable = Arrays.asList("a", "b", "c");
    List<String> test = Guavate.stream(iterable)
        .collect(Collectors.toList());
    assertThat(test).isEqualTo(ImmutableList.of("a", "b", "c"));
  }