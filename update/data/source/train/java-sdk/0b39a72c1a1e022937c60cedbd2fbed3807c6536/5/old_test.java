@Test
    public void testListDocuments() {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put(ConceptInsights.ACCOUNT_ID, "public");
        params.put(ConceptInsights.CORPUS, "ibmresearcher");
        params.put(ConceptInsights.CURSOR, 0);
        params.put(ConceptInsights.LIMIT, 20);

        Documents documents = service.listDocuments(params);
        Assert.assertNotNull(documents);
    }