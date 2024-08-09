@Test
  @Category(NeedsRunner.class)
  public void testWrite() throws IOException {
    List<String> inputs =
        Arrays.asList(
            "Critical canary",
            "Apprehensive eagle",
            "Intimidating pigeon",
            "Pedantic gull",
            "Frisky finch");
    runWrite(
        inputs,
        IDENTITY_MAP,
        getBaseOutputFilename(),
        WriteFiles.to(makeSimpleSink(), SerializableFunctions.<String>identity()));
  }