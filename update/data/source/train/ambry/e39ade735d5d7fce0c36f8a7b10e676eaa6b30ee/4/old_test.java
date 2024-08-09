@Test(expected = IllegalStateException.class)
  public void handleGetTest()
      throws IOException, JSONException, RestServiceException, URISyntaxException {
    AdminBlobStorageService adminBlobStorageService = getAdminBlobStorageService();
    MessageInfo messageInfo = createMessageInfo(RestMethod.GET, "/", new JSONObject());
    adminBlobStorageService.handleGet(messageInfo);
  }