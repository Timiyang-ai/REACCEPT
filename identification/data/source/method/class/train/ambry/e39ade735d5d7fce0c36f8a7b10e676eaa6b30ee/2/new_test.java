@Test(expected = IllegalStateException.class)
  public void handlePostTest()
      throws JSONException, RestServiceException, URISyntaxException {
    RestRequestInfo restRequestInfo = createRestRequestInfo(RestMethod.POST, "/", new JSONObject());
    adminBlobStorageService.handlePost(restRequestInfo);
  }