  @Test
  public void test_combinedWith() {
    PropertySet base = PropertySet.of(ImmutableListMultimap.of("a", "x", "a", "y", "c", "z"));
    PropertySet other = PropertySet.of(ImmutableListMultimap.of("a", "aa", "b", "bb", "d", "dd"));
    PropertySet expected = PropertySet.of(ImmutableListMultimap.of("a", "x", "a", "y", "c", "z", "b", "bb", "d", "dd"));
    assertThat(base.combinedWith(other)).isEqualTo(expected);
  }