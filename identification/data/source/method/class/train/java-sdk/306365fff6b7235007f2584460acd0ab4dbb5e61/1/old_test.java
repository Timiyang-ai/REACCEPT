@Test
    public void testGetDocumentCollection() {
        // Expected Mock response
        DocumentCollection docCollWithQueryResponse = new DocumentCollection();
        List<Document> documentsWithQuery = new ArrayList<Document>();
        String docId = UUID.randomUUID().toString();
        String docName = "documentName";
        Document doc = createMockDocument(docId, docName, MediaType.TEXT_HTML);
        String docContent = "<html><title>Test</title><body><text>test document</text></body></html>";
        documentsWithQuery.add(doc);
        List<Link> links = new ArrayList<Link>();
        docCollWithQueryResponse.setLinks(links);

        // Call get documents with query parameters
        List<Parameter> queryParams = new ArrayList<Parameter>();
        queryParams.add(new Parameter("name", docName));
        queryParams.add(new Parameter("limit", "5"));
        mockServer.reset().when(request()
                .withPath(DOCUMENTS_PATH).withQueryStringParameters(queryParams))
                .respond(response().withBody(GsonSingleton.getGson().toJson(docCollWithQueryResponse))
                );
        DocumentCollection docCollWithQuery = service.getDocumentCollection(null, 5, docName, null, null);
        Assert.assertNotNull(docCollWithQuery);
        Assert.assertEquals(docCollWithQuery.toString(), docCollWithQueryResponse.toString());

        // Call get document
        mockServer.when(request().withPath(DOCUMENTS_PATH + "/" + docId)).respond(
                response().withBody(docContent)
        );
        String document1 = service.getDocument(docId);
        Assert.assertNotNull(document1);
        Assert.assertEquals(document1, docContent);
    }