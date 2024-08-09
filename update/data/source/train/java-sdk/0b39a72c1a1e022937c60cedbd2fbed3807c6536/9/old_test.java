@Test
    public void testGetDocumentRelatedConcepts() {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put(ConceptInsights.ACCOUNT_ID, "public");
        params.put(ConceptInsights.CORPUS, "ibmresearcher");
        params.put(ConceptInsights.DOCUMENT, "il-AHARONA");
        params.put(ConceptInsights.LEVEL, "1");
        params.put(ConceptInsights.LIMIT, 10);
        Concepts concepts =  service.getDocumentRelatedConcepts(params);
        Assert.assertNotNull(concepts);
    }