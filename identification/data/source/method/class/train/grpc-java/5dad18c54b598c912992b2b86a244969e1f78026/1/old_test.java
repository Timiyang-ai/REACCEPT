  @Test
  public void lookupDecompressor_checkDefaultMessageEncodingsExist() {
    // Explicitly put the names in, rather than link against MessageEncoding
    assertNotNull("Expected identity to be registered",
        DecompressorRegistry.getDefaultInstance().lookupDecompressor("identity"));
    assertNotNull("Expected gzip to be registered",
        DecompressorRegistry.getDefaultInstance().lookupDecompressor("gzip"));
  }