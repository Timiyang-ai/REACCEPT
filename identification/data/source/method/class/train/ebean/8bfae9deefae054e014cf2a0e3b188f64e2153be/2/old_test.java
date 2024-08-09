  @Test
  public void sameByValue() throws Exception {

    assertThat(Same.sameByValue("a", "a")).isTrue();
    assertThat(Same.sameByValue("a", "b")).isFalse();
    assertThat(Same.sameByValue(null, null)).isTrue();
    assertThat(Same.sameByValue("a", null)).isFalse();
    assertThat(Same.sameByValue(null, "a")).isFalse();
  }