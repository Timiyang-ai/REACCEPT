  @Test public void clientListing() throws Exception {
    Set<String> clientsBefore = listing();
    Set<String> expected = ImmutableSet.<String>builder()
        .addAll(clientsBefore)
        .add("client4")
        .build();

    create(CreateClientRequestV2.builder().name("client4").build());
    assertThat(listing()).containsAll(expected);
  }