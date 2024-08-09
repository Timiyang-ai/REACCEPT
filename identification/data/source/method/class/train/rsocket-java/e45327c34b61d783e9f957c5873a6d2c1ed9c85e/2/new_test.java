  @DisplayName("returns the server by name")
  @Test
  void findServer() {
    LocalServerTransport serverTransport = LocalServerTransport.createEphemeral();

    serverTransport
        .start(duplexConnection -> Mono.empty())
        .as(StepVerifier::create)
        .expectNextCount(1)
        .verifyComplete();

    assertThat(LocalServerTransport.findServer(serverTransport.getName())).isNotNull();
  }