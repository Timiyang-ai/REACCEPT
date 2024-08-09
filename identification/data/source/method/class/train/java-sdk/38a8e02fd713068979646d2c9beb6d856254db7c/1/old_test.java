@Test
  public void testGetTone() {
    final String text =
        "I know the times are difficult! Our sales have been "
            + "disappointing for the past three quarters for our data analytics "
            + "product suite. We have a competitive data analytics product "
            + "suite in the industry. But we need to do our job selling it! ";

    final ToneAnalysis response = new ToneAnalysis();

    final ElementTone docTone = new ElementTone();
    docTone.addTone(buildEmotionTone());
    docTone.addTone(buildWritingTone());
    docTone.addTone(buildSocialTone());
   
    List<SentenceAnalysis> sentences = new ArrayList<SentenceAnalysis>();
    response.setSentencesTone(sentences);

    final SentenceAnalysis sentence = new SentenceAnalysis(0, 0, text.length(), text);
    sentence.addTone(buildEmotionTone());
    sentence.addTone(buildWritingTone());
    sentence.addTone(buildSocialTone());
    
    final JsonObject contentJson = new JsonObject();
    contentJson.addProperty("text", text);

    mockServer
        .when(request().withMethod(POST).withPath(TONE_PATH).withBody(contentJson.toString()))
        .respond(
            response().withHeaders(
                new Header(HttpHeaders.Names.CONTENT_TYPE, HttpMediaType.APPLICATION_JSON))
                .withBody(GsonSingleton.getGson().toJson(response)));

    // Call the service and get the tone
    final ToneAnalysis tone = service.getTone(text);
    Assert.assertNotNull(tone);
    Assert.assertNotNull(tone.getDocumentTone());
    Assert.assertEquals(3, tone.getDocumentTone().getTones().size());
    Assert.assertNotNull(tone.getSentencesTone());
    Assert.assertEquals(1, tone.getSentencesTone().size());
    Assert.assertNotNull(tone.getSentencesTone().get(0).getText());
    Assert.assertEquals(tone, response);

  }