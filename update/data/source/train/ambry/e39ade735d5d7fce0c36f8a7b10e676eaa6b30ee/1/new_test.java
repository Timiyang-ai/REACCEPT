@Test(expected = IllegalStateException.class)
  public void handleHeadTest()
      throws JSONException, RestServiceException, URISyntaxException {
    RestRequestInfo restRequestInfo = createRestRequestInfo(RestMethod.HEAD, "/", new JSONObject());
    adminBlobStorageService.handleHead(restRequestInfo);
  }