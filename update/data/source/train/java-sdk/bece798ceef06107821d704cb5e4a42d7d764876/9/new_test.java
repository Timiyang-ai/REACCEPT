@Test
  public void testGetTone() {
    final String text =
        "I know the times are difficult! Our sales have been "
            + "disappointing for the past three quarters for our data analytics "
            + "product suite. We have a competitive data analytics product "
            + "suite in the industry. But we need to do our job selling it! ";

    final Tone response = new Tone();
    response.setScorecard("email");

    final ToneDimension toneDimension = new ToneDimension();
    toneDimension.setId("emotion_tone");
    toneDimension.setName("Emotion Tone");
    response.setChildren(new ArrayList<ToneDimension>() {
      {
        add(toneDimension);
      }
    });

    final ToneTrait toneTrait = new ToneTrait();
    toneTrait.setId("Cheerfulness");
    toneTrait.setName("Cheerfulness");
    toneTrait.setNormalizedScore(0.6966722608487173);
    toneTrait.setRawScore(0.013608247422680412);
    toneTrait.setWordCount(2);
    toneDimension.setChildren(new ArrayList<ToneTrait>() {
      {
        add(toneTrait);
      }
    });

    final LinguisticEvidence linguisticEvidence = new LinguisticEvidence();
    linguisticEvidence.setCorrelation("positive");
    linguisticEvidence.setEvidenceScore(0.6966722608487173);
    linguisticEvidence.setWordCount(2);
    linguisticEvidence.setWords(new ArrayList<String>() {
      {
        add("challenges");
        add("improve");
      }
    });

    final LinguisticEvidence linguisticEvidence1 = new LinguisticEvidence();
    linguisticEvidence1.setCorrelation("positive");
    linguisticEvidence1.setEvidenceScore(0.6966722608487173);
    linguisticEvidence1.setWordCount(2);
    linguisticEvidence1.setWords(new ArrayList<String>() {
      {
        add("challenges");
        add("improve");
      }
    });

    final LinguisticEvidence linguisticEvidence2 = new LinguisticEvidence();
    linguisticEvidence2.setCorrelation("positive");
    linguisticEvidence2.setEvidenceScore(0.6966722608487173);
    linguisticEvidence2.setWordCount(2);
    linguisticEvidence2.setWords(new ArrayList<String>() {
      {
        add("challenges");
        add("improve");
      }
    });


    toneTrait.setLinguisticEvidence(new ArrayList<LinguisticEvidence>() {
      {
        add(linguisticEvidence);
        add(linguisticEvidence1);
        add(linguisticEvidence2);
      }
    });

    final JsonObject contentJson = new JsonObject();
    contentJson.addProperty(ToneAnalyzer.TEXT, text);

    contentJson.addProperty(ToneAnalyzer.SCORECARD, Scorecard.EMAIL.getId());

    mockServer.when(
        request().withMethod("POST").withPath(TONE_PATH).withBody(contentJson.toString())).respond(
        response().withHeaders(
            new Header(HttpHeaders.Names.CONTENT_TYPE, HttpMediaType.APPLICATION_JSON)).withBody(
            GsonSingleton.getGson().toJson(response)));

    // Call the service and get the tone
    final Tone tone = service.getTone(text, Scorecard.EMAIL);
    Assert.assertNotNull(tone);
    Assert.assertNotNull(tone.getChildren());
    Assert.assertEquals(tone, response);

  }