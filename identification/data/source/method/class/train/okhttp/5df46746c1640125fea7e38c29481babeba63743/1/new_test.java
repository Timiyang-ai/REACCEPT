  @Test public void username() throws Exception {
    assertThat(parse("http://@host/path")).isEqualTo(parse("http://host/path"));
    assertThat(parse("http://user@host/path")).isEqualTo(parse("http://user@host/path"));
  }