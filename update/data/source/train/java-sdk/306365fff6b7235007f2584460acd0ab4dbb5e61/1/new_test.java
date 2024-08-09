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
                .withPath(DocumentConversion.DOCUMENTS_PATH).withQueryStringParameters(queryParams))
                .respond(response().withBody(GsonSingleton.getGson().toJson(docCollWithQueryResponse))
                );
        Map<String, Object> docListParams = new HashMap<String, Object>();
        docListParams.put(DocumentConversion.LIMIT, 5);
        docListParams.put(DocumentConversion.NAME, docName);
        DocumentCollection docCollWithQuery = service.getDocumentCollection(docListParams);
        Assert.assertNotNull(docCollWithQuery);
        Assert.assertEquals(docCollWithQuery.toString(), docCollWithQueryResponse.toString());

        // Call get document
        mockServer.when(request().withPath(DocumentConversion.DOCUMENTS_PATH + "/" + docId)).respond(
                response().withBody(docContent)
        );
        String document1 = ConversionUtils.writeInputStreamToString(service.getDocument(docId));
        Assert.assertNotNull(document1);
        Assert.assertEquals(document1, docContent);
    }