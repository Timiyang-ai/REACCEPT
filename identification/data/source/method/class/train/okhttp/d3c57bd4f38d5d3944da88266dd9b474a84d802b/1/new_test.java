  @Test public void scheme() throws Exception {
    assertThat(parse("http://host/")).isEqualTo(parse("http://host/"));
    assertThat(parse("Http://host/")).isEqualTo(parse("http://host/"));
    assertThat(parse("http://host/")).isEqualTo(parse("http://host/"));
    assertThat(parse("HTTP://host/")).isEqualTo(parse("http://host/"));
    assertThat(parse("https://host/")).isEqualTo(parse("https://host/"));
    assertThat(parse("HTTPS://host/")).isEqualTo(parse("https://host/"));

    assertInvalid("image640://480.png", "Expected URL scheme 'http' or 'https' but was 'image640'");
    assertInvalid("httpp://host/", "Expected URL scheme 'http' or 'https' but was 'httpp'");
    assertInvalid("0ttp://host/", "Expected URL scheme 'http' or 'https' but no colon was found");
    assertInvalid("ht+tp://host/", "Expected URL scheme 'http' or 'https' but was 'ht+tp'");
    assertInvalid("ht.tp://host/", "Expected URL scheme 'http' or 'https' but was 'ht.tp'");
    assertInvalid("ht-tp://host/", "Expected URL scheme 'http' or 'https' but was 'ht-tp'");
    assertInvalid("ht1tp://host/", "Expected URL scheme 'http' or 'https' but was 'ht1tp'");
    assertInvalid("httpss://host/", "Expected URL scheme 'http' or 'https' but was 'httpss'");
  }