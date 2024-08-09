  @Test public void modifyClient_notFound() throws Exception {
    ModifyClientRequestV2 request = ModifyClientRequestV2.forName("non-existent");
    RequestBody body = RequestBody.create(JSON, mapper.writeValueAsString(request));
    Request post = clientRequest("/automation/v2/clients/non-existent").post(body).build();
    Response httpResponse = mutualSslClient.newCall(post).execute();
    assertThat(httpResponse.code()).isEqualTo(404);
  }