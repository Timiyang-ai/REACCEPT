@Test
  public void test_overrideWith() {
    PropertySet base = PropertySet.of(ImmutableListMultimap.of("a", "x", "a", "y", "b", "y", "c", "z"));
    PropertySet other = PropertySet.of(ImmutableListMultimap.of("a", "aa", "c", "cc", "d", "dd", "e", "ee"));
    PropertySet expected =
        PropertySet.of(ImmutableListMultimap.of("a", "aa", "b", "y", "c", "cc", "d", "dd", "e", "ee"));
    assertThat(base.overrideWith(other)).isEqualTo(expected);
  }