  @Test public void decodePassword() {
    assertThat(parse("http://user:password@host/").password()).isEqualTo("password");
    assertThat(parse("http://user:@host/").password()).isEqualTo("");
    assertThat(parse("http://user:%F0%9F%8D%A9@host/").password())
        .isEqualTo("\uD83C\uDF69");
  }