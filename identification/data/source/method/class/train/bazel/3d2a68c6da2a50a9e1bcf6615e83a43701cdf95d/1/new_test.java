  @Test
  public void beforeEach_empty() {
    assertThat(Interspersing.beforeEach("foo", ImmutableList.<String>of())).isEmpty();
  }