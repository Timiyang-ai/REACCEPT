  @Test
  public void sameByNull() throws Exception {

    assertThat(Same.sameByNull("a", "a")).isTrue();
    assertThat(Same.sameByNull("a", "b")).isTrue();
    assertThat(Same.sameByNull(null, null)).isTrue();
    assertThat(Same.sameByNull("a", null)).isFalse();
    assertThat(Same.sameByNull(null, "a")).isFalse();
  }