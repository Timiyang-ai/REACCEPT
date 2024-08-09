  @Test
  public void test_combinedWith() {
    ValueWithFailures<List<String>> base = ValueWithFailures.of(ImmutableList.of("a"), ImmutableList.of(FAILURE1));
    ValueWithFailures<List<String>> other =
        ValueWithFailures.of(ImmutableList.of("b", "c"), ImmutableList.of(FAILURE2));
    ValueWithFailures<List<String>> test = base.combinedWith(other, Guavate::concatToList);
    assertThat(test.getValue()).isEqualTo(ImmutableList.of("a", "b", "c"));
    assertThat(test.getFailures()).isEqualTo(ImmutableList.of(FAILURE1, FAILURE2));
  }