  @Test
  public void test_ensureOnlyOne() {
    assertThat(Stream.empty().reduce(Guavate.ensureOnlyOne())).isEqualTo(Optional.empty());
    assertThat(Stream.of("a").reduce(Guavate.ensureOnlyOne())).isEqualTo(Optional.of("a"));
    assertThatIllegalArgumentException().isThrownBy(() -> Stream.of("a", "b").reduce(Guavate.ensureOnlyOne()));
  }