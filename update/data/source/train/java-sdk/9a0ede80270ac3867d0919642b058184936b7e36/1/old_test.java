@Test
  public void testCreateCustomization() throws InterruptedException, FileNotFoundException {
    Customization customization =
        loadFixture("src/test/resources/speech_to_text/customization.json", Customization.class);

    server.enqueue(
        new MockResponse().addHeader(CONTENT_TYPE, HttpMediaType.APPLICATION_JSON).setBody(GSON.toJson(customization)));

    Customization result = service
        .createCustomization(customization.getName(), SpeechModel.EN_UK_BROADBANDMODEL, customization.getDescription())
        .execute();
    final RecordedRequest request = server.takeRequest();

    assertEquals("POST", request.getMethod());
    assertEquals(PATH_CUSTOMIZATIONS, request.getPath());
    assertEquals(result.toString(), customization.toString());
  }