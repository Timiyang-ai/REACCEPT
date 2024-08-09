  @Test public void parse() throws Exception {
    String header = "no-cache, no-store, max-age=1, s-maxage=2, private, public, must-revalidate, "
        + "max-stale=3, min-fresh=4, only-if-cached, no-transform";
    CacheControl cacheControl = CacheControl.parse(new Headers.Builder()
        .set("Cache-Control", header)
        .build());
    assertThat(cacheControl.noCache()).isTrue();
    assertThat(cacheControl.noStore()).isTrue();
    assertThat(cacheControl.maxAgeSeconds()).isEqualTo(1);
    assertThat(cacheControl.sMaxAgeSeconds()).isEqualTo(2);
    assertThat(cacheControl.isPrivate()).isTrue();
    assertThat(cacheControl.isPublic()).isTrue();
    assertThat(cacheControl.mustRevalidate()).isTrue();
    assertThat(cacheControl.maxStaleSeconds()).isEqualTo(3);
    assertThat(cacheControl.minFreshSeconds()).isEqualTo(4);
    assertThat(cacheControl.onlyIfCached()).isTrue();
    assertThat(cacheControl.noTransform()).isTrue();
    assertThat(cacheControl.toString()).isEqualTo(header);
  }