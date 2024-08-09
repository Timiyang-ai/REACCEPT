@Test(expected = IllegalStateException.class)
  public void handleDeleteTest()
      throws JSONException, RestServiceException, URISyntaxException {
    RestRequestInfo restRequestInfo = createRestRequestInfo(RestMethod.DELETE, "/", new JSONObject());
    adminBlobStorageService.handleDelete(restRequestInfo);
  }