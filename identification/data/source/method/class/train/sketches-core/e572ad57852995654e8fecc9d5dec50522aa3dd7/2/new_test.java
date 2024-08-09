  @Test
  public void getMaxSerializedSizeBytes() {
    final int sizeBytes =
        KllFloatsSketch.getMaxSerializedSizeBytes(KllFloatsSketch.DEFAULT_K, 1_000_000_000);
    assertEquals(sizeBytes, 3160);
  }