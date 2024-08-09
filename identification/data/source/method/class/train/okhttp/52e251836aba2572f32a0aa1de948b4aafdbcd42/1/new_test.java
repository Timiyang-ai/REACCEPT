  @Test public void parseAll() throws Exception {
    Headers headers = new Headers.Builder()
        .add("Set-Cookie: a=b")
        .add("Set-Cookie: c=d")
        .build();
    List<Cookie> cookies = Cookie.parseAll(url, headers);
    assertThat(cookies.size()).isEqualTo(2);
    assertThat(cookies.get(0).toString()).isEqualTo("a=b; path=/");
    assertThat(cookies.get(1).toString()).isEqualTo("c=d; path=/");
  }