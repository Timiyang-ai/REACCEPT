@Test(expected = IllegalStateException.class)
  public void handleDeleteTest()
      throws IOException, JSONException, RestServiceException, URISyntaxException {
    AdminBlobStorageService adminBlobStorageService = getAdminBlobStorageService();
    MessageInfo messageInfo = createMessageInfo(RestMethod.DELETE, "/", new JSONObject());
    adminBlobStorageService.handleMessage(messageInfo);
  }