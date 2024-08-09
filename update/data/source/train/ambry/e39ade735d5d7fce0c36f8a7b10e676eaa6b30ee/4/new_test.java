@Test(expected = IllegalStateException.class)
  public void handleGetTest()
      throws JSONException, RestServiceException, URISyntaxException {
    RestRequestInfo restRequestInfo = createRestRequestInfo(RestMethod.GET, "/", new JSONObject());
    adminBlobStorageService.handleGet(restRequestInfo);
  }