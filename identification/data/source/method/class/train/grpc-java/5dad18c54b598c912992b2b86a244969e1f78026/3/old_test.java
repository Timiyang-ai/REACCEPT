  @Test
  public void getKnownMessageEncodings_checkDefaultMessageEncodingsExist() {
    Set<String> knownEncodings = new HashSet<>();
    knownEncodings.add("identity");
    knownEncodings.add("gzip");

    assertEquals(knownEncodings,
        DecompressorRegistry.getDefaultInstance().getKnownMessageEncodings());
  }