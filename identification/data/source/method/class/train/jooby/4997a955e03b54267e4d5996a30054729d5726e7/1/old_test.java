  @Test
  public void allowedMethods() {
    cors(baseconf().withValue("methods", fromAnyRef("GET")), cors -> {
      assertEquals(true, cors.allowMethod("GET"));
      assertEquals(true, cors.allowMethod("get"));
      assertEquals(false, cors.allowMethod("POST"));
    });

    cors(baseconf().withValue("methods", fromAnyRef(asList("get", "post"))), cors -> {
      assertEquals(true, cors.allowMethod("GET"));
      assertEquals(true, cors.allowMethod("get"));
      assertEquals(true, cors.allowMethod("POST"));
    });
  }