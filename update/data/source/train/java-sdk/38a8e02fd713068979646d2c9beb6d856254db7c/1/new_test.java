@Test
  public void testGetTone() throws FileNotFoundException {
    final String text = "I know the times are difficult! Our sales have been "
        + "disappointing for the past three quarters for our data analytics "
        + "product suite. We have a competitive data analytics product "
        + "suite in the industry. But we need to do our job selling it! ";

    ToneAnalysis response =
        loadFixture(FIXTURE, ToneAnalysis.class);

    final JsonObject contentJson = new JsonObject();
    contentJson.addProperty(TEXT, text);

    mockServer
      .when(request()
          .withMethod(POST)
          .withPath(TONE_PATH)
          .withQueryStringParameter(VERSION_DATE, ToneAnalyzer.VERSION_DATE_2016_02_11)
          .withBody(contentJson.toString()))
      .respond(response()
          .withHeader(HttpHeaders.CONTENT_TYPE, HttpMediaType.APPLICATION_JSON)
          .withBody(response.toString()));

    // Call the service and compare the result
    Assert.assertEquals(response, service.getTone(text));
  }