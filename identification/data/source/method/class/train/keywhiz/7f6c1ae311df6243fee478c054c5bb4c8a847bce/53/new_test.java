  @Test public void createClient_success() throws Exception {
    Response httpResponse = create(CreateClientRequestV2.builder().name("client1").build());
    assertThat(httpResponse.code()).isEqualTo(201);
    URI location = URI.create(httpResponse.header(LOCATION));
    assertThat(location.getPath()).isEqualTo("/automation/v2/clients/client1");
  }