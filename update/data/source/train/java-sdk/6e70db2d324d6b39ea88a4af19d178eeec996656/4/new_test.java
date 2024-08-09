@Test
  public void testClassify() throws InterruptedException {
    final JsonObject contentJson = new JsonObject();
    contentJson.addProperty(TEXT, classification.getText());

    final String path = String.format(CLASSIFY_PATH, classifierId);

    server.enqueue(jsonResponse(classification));
    ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
        .classifierId(classifierId)
        .text(classification.getText())
        .build();
    final Classification result = service.classify(classifyOptions).execute();
    final RecordedRequest request = server.takeRequest();

    assertEquals(path, request.getPath());
    assertEquals("POST", request.getMethod());
    assertEquals(contentJson.toString(), request.getBody().readUtf8());
    assertEquals(classification, result);
  }