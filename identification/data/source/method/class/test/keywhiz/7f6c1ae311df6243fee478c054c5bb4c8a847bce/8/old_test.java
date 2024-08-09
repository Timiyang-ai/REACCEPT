  @Test public void clientSecretsListing_notFound() throws Exception {
    Request get = clientRequest("/automation/v2/clients/non-existent/secrets").get().build();
    Response httpResponse = mutualSslClient.newCall(get).execute();
    assertThat(httpResponse.code()).isEqualTo(404);
  }