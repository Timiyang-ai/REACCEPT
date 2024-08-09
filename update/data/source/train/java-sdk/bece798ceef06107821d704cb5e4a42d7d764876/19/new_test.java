@Test
  public void testConvertDocument() throws URISyntaxException, IOException {
    final File expAnswerFile =
        new File(
            "src/test/resources/document_conversion/html-with-extra-content-input-to-answer.json");
    final File html =
        new File("src/test/resources/document_conversion/html-with-extra-content-input.htm");
    final byte[] expAnswer = IOUtils.toByteArray(new FileInputStream(expAnswerFile));

    mockServer.when(request().withMethod("POST").withPath(CONVERT_DOCUMENT_PATH)).respond(
        response().withBody(expAnswer));

    final Answers convertedDoc = service.convertDocumentToAnswer(html, null);
    Assert.assertNotNull(convertedDoc);

    // Convert document with a specified media type
    final Answers convertWithMediaType =
        service.convertDocumentToAnswer(html, HttpMediaType.TEXT_HTML);
    Assert.assertNotNull(convertWithMediaType);
  }