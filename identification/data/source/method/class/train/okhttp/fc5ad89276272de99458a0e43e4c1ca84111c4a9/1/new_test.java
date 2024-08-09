  @Test public void addPathSegments() throws Exception {
    HttpUrl base = parse("http://host/a/b/c");

    // Add a string with zero slashes: resulting URL gains one slash.
    assertThat(base.newBuilder().addPathSegments("").build().encodedPath())
        .isEqualTo("/a/b/c/");
    assertThat(base.newBuilder().addPathSegments("d").build().encodedPath())
        .isEqualTo("/a/b/c/d");

    // Add a string with one slash: resulting URL gains two slashes.
    assertThat(base.newBuilder().addPathSegments("/").build().encodedPath())
        .isEqualTo("/a/b/c//");
    assertThat(base.newBuilder().addPathSegments("d/").build().encodedPath())
        .isEqualTo("/a/b/c/d/");
    assertThat(base.newBuilder().addPathSegments("/d").build().encodedPath())
        .isEqualTo("/a/b/c//d");

    // Add a string with two slashes: resulting URL gains three slashes.
    assertThat(base.newBuilder().addPathSegments("//").build().encodedPath())
        .isEqualTo("/a/b/c///");
    assertThat(base.newBuilder().addPathSegments("/d/").build().encodedPath())
        .isEqualTo("/a/b/c//d/");
    assertThat(base.newBuilder().addPathSegments("d//").build().encodedPath())
        .isEqualTo("/a/b/c/d//");
    assertThat(base.newBuilder().addPathSegments("//d").build().encodedPath())
        .isEqualTo("/a/b/c///d");
    assertThat(base.newBuilder().addPathSegments("d/e/f").build().encodedPath())
        .isEqualTo("/a/b/c/d/e/f");
  }