@Test(expected = IllegalStateException.class)
  public void handlePostTest()
      throws IOException, JSONException, RestServiceException, URISyntaxException {
    AdminBlobStorageService adminBlobStorageService = getAdminBlobStorageService();
    MessageInfo messageInfo = createMessageInfo(RestMethod.POST, "/", new JSONObject());
    adminBlobStorageService.handlePost(messageInfo);
  }