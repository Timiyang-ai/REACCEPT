@Test(expected = IllegalStateException.class)
  public void handleHeadTest()
      throws IOException, JSONException, RestServiceException, URISyntaxException {
    AdminBlobStorageService adminBlobStorageService = getAdminBlobStorageService();
    MessageInfo messageInfo = createMessageInfo(RestMethod.HEAD, "/", new JSONObject());
    adminBlobStorageService.handleHead(messageInfo);
  }