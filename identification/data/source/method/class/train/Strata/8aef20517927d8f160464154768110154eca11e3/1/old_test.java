  @Test
  public void test_parseTenor() {
    assertThat(LoaderUtils.parseTenor("P2D")).isEqualTo(Tenor.ofDays(2));
    assertThat(LoaderUtils.parseTenor("2D")).isEqualTo(Tenor.ofDays(2));
    assertThatIllegalArgumentException().isThrownBy(() -> LoaderUtils.parseTenor("2"));
  }