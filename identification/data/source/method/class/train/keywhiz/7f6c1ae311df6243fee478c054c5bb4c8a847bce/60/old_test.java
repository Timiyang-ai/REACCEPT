  @Test public void clientGroupsListing_notFound() throws Exception {
    Request get = clientRequest("/automation/v2/clients/non-existent/groups").get().build();
    Response httpResponse = mutualSslClient.newCall(get).execute();
    assertThat(httpResponse.code()).isEqualTo(404);
  }