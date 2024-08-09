@Test
    public void testUploadDocument() throws URISyntaxException, IOException {
        String docId = UUID.randomUUID().toString();
        Document response = createMockDocument(docId, "html-with-extra-content-input.htm", MediaType.TEXT_HTML);
        File html = new File("src/test/resources/document_conversion/html-with-extra-content-input.htm");

        mockServer.when(
                request().withMethod("POST").withPath(DocumentConversion.DOCUMENTS_PATH)
        ).respond((response().withBody(GsonSingleton.getGson().toJson(response))));

        // Call upload document
        Document document = service.uploadDocument(html);

        Assert.assertNotNull(document);
        Assert.assertEquals(document.toString(), response.toString());
    }