  @Test public void addEncodedPathSegments() throws Exception {
    HttpUrl base = parse("http://host/a/b/c");
    assertThat(
        (Object) base.newBuilder().addEncodedPathSegments("d/e/%20/\n").build().encodedPath())
        .isEqualTo("/a/b/c/d/e/%20/");
  }