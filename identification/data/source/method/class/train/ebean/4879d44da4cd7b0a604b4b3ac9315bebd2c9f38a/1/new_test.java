  @Test
  public void trim() throws Exception {

    assertThat(TrimLogSql.trim("hello")).isEqualTo("hello");
    assertThat(TrimLogSql.trim("hello\nthere")).isEqualTo("hello\\n there");
  }