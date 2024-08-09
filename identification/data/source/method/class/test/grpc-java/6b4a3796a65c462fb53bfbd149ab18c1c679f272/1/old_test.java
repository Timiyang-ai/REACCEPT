  @Test
  public void parseTxtResults_misspelledName() throws Exception {
    List<String> txtRecords = new ArrayList<>();
    txtRecords.add("some_record");
    txtRecords.add("_grpc_config=[]");

    List<? extends Map<String, ?>> results = DnsNameResolver.parseTxtResults(txtRecords);

    assertThat(results).isEmpty();
  }