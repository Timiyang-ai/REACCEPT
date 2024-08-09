@Test
    public void testUploadDocument() throws URISyntaxException, IOException {
        String docId = UUID.randomUUID().toString();
        String pdfDocId = UUID.randomUUID().toString();
        Document response = createMockDocument(docId, "html-with-extra-content-input.htm", MediaType.TEXT_HTML);
        Document pdfResponse = createMockDocument(pdfDocId, "pdf-with-sections-input.pdf", MediaType.APPLICATION_PDF);
        File html = new File("src/test/resources/document_conversion/html-with-extra-content-input.htm");
        File pdf = new File("src/test/resources/document_conversion/pdf-with-sections-input.pdf");

        mockServer.when(
                request().withMethod("POST").withPath(DOCUMENTS_PATH)
        ).respond((response().withBody(GsonSingleton.getGson().toJson(response))));

        // Call upload document
        Document document = service.uploadDocument(html);

        Assert.assertNotNull(document);
        Assert.assertEquals(document.toString(), response.toString());

        mockServer.reset().when(
                request().withMethod("POST").withPath(DOCUMENTS_PATH)
        ).respond((response().withBody(GsonSingleton.getGson().toJson(pdfResponse))));

        // Call upload document with media type
        Document pdfDocument = service.uploadDocument(pdf, MediaType.APPLICATION_PDF);
        Assert.assertNotNull(pdfDocument);
        Assert.assertEquals(pdfDocument.toString(), pdfResponse.toString());
    }