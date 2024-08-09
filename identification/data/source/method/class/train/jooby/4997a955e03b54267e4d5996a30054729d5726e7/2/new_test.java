  @Test
  public void origin() {
    cors(baseconf().withValue("origin", fromAnyRef("*")), cors -> {
      assertEquals(true, cors.anyOrigin());
      assertEquals(true, cors.allowOrigin("http://foo.com"));
    });

    cors(baseconf().withValue("origin", fromAnyRef("http://*.com")), cors -> {
      assertEquals(false, cors.anyOrigin());
      assertEquals(true, cors.allowOrigin("http://foo.com"));
      assertEquals(true, cors.allowOrigin("http://bar.com"));
    });

    cors(baseconf().withValue("origin", fromAnyRef("http://foo.com")), cors -> {
      assertEquals(false, cors.anyOrigin());
      assertEquals(true, cors.allowOrigin("http://foo.com"));
      assertEquals(false, cors.allowOrigin("http://bar.com"));
    });
  }