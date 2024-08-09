  @Test
  public void valueOf() {
    MediaType type = MediaType.valueOf("application / json; q=0.5; charset=us-ascii");
    assertEquals("application / json; q=0.5; charset=us-ascii", type.toString());
    assertEquals("application / json", type.getValue());
    assertEquals("application", type.getType());
    assertEquals("json", type.getSubtype());
    assertEquals(.5f, type.getQuality());
    assertEquals("us-ascii", type.getCharset().name().toLowerCase());

    MediaType any = MediaType.valueOf("*");
    assertEquals("*/*", any.getValue());
    assertEquals("*", any.getType());
    assertEquals("*", any.getSubtype());

    any = MediaType.valueOf("");
    assertEquals("*/*", any.getValue());
    assertEquals("*", any.getType());
    assertEquals("*", any.getSubtype());

    any = MediaType.valueOf(null);
    assertEquals("*/*", any.getValue());
    assertEquals("*", any.getType());
    assertEquals("*", any.getSubtype());
  }