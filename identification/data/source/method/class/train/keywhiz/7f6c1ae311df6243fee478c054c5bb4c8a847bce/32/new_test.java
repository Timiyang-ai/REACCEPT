  @Test public void modifySecretGroups_notFound() throws Exception {
    ModifyGroupsRequestV2 request = ModifyGroupsRequestV2.builder().build();
    RequestBody body = RequestBody.create(JSON, mapper.writeValueAsString(request));
    Request put = clientRequest("/automation/v2/secrets/non-existent/groups").put(body).build();
    Response httpResponse = mutualSslClient.newCall(put).execute();
    assertThat(httpResponse.code()).isEqualTo(404);
  }