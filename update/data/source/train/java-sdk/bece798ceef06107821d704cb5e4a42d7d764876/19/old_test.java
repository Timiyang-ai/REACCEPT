@Test
    public void testConvertDocument() throws URISyntaxException, IOException {
        File expAnswerFile = new File("src/test/resources/document_conversion/html-with-extra-content-input-to-answer.json");
        File html = new File("src/test/resources/document_conversion/html-with-extra-content-input.htm");
        byte[] expAnswer = IOUtils.toByteArray(new FileInputStream(expAnswerFile));

        mockServer.when(
                request().withMethod("POST").withPath(CONVERT_DOCUMENT_PATH)
        ).respond(response().withBody(expAnswer));

        Answers convertedDoc = service.convertDocumentToAnswer(html,null);
        Assert.assertNotNull(convertedDoc);
        
        // Convert document with a specified media type
        Answers convertWithMediaType = service.convertDocumentToAnswer(html, MediaType.TEXT_HTML);
        Assert.assertNotNull(convertWithMediaType);
	}