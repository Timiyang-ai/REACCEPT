  @Test public void port() throws Exception {
    assertThat(parse("http://host:80/")).isEqualTo(parse("http://host/"));
    assertThat(parse("http://host:99/")).isEqualTo(parse("http://host:99/"));
    assertThat(parse("http://host:/")).isEqualTo(parse("http://host/"));
    assertThat(parse("http://host:65535/").port()).isEqualTo(65535);
    assertInvalid("http://host:0/", "Invalid URL port: \"0\"");
    assertInvalid("http://host:65536/", "Invalid URL port: \"65536\"");
    assertInvalid("http://host:-1/", "Invalid URL port: \"-1\"");
    assertInvalid("http://host:a/", "Invalid URL port: \"a\"");
    assertInvalid("http://host:%39%39/", "Invalid URL port: \"%39%39\"");
  }