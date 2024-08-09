  @Test public void topPrivateDomain() {
    assertThat(parse("https://google.com").topPrivateDomain()).isEqualTo("google.com");
    assertThat(parse("https://adwords.google.co.uk").topPrivateDomain())
        .isEqualTo("google.co.uk");
    assertThat(parse("https://栃.栃木.jp").topPrivateDomain())
        .isEqualTo("xn--ewv.xn--4pvxs.jp");
    assertThat(parse("https://xn--ewv.xn--4pvxs.jp").topPrivateDomain())
        .isEqualTo("xn--ewv.xn--4pvxs.jp");

    assertThat(parse("https://co.uk").topPrivateDomain()).isNull();
    assertThat(parse("https://square").topPrivateDomain()).isNull();
    assertThat(parse("https://栃木.jp").topPrivateDomain()).isNull();
    assertThat(parse("https://xn--4pvxs.jp").topPrivateDomain()).isNull();
    assertThat(parse("https://localhost").topPrivateDomain()).isNull();
    assertThat(parse("https://127.0.0.1").topPrivateDomain()).isNull();
  }