  @Test public void groupInfo_notFound() throws Exception {
    Request get = clientRequest("/automation/v2/groups/non-existent").get().build();
    Response httpResponse = mutualSslClient.newCall(get).execute();
    assertThat(httpResponse.code()).isEqualTo(404);
  }