  @Test
  public void escapeJsonForStringEmbedding() {
    assertEquals(escaped, JsUtil.escapeJsonForStringEmbedding(unescaped));
  }