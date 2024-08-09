  @Test
  public void createString_DisallowEmpty() {
    thrown.expect(IllegalArgumentException.class);
    TagKey.create("");
  }