  @Test
  public void prependEach_empty() {
    assertThat(Interspersing.prependEach("foo", ImmutableList.<String>of())).isEmpty();
  }