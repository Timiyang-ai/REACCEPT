@Test
    public void testGetDocumentProcessingState() {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put(ConceptInsights.ACCOUNT_ID, "public");
        params.put(ConceptInsights.CORPUS, "ibmresearcher");
        params.put(ConceptInsights.DOCUMENT, "il-AHARONA");
        DocumentProcessingStatus documentProcessingState = service.getDocumentProcessingState("public", "ibmresearcher", "il-AHARONA");
        Assert.assertNotNull(documentProcessingState);
    }