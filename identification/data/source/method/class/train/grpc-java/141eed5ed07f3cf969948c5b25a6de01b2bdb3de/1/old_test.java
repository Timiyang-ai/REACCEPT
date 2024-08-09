  @Test
  public void discardAll() {
    Fish lance = new Fish(LANCE);
    Metadata metadata = new Metadata();

    metadata.put(KEY, lance);
    metadata.discardAll(KEY);
    assertNull(metadata.getAll(KEY));
    assertNull(metadata.get(KEY));
  }