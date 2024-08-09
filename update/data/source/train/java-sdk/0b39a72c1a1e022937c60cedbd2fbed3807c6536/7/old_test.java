@Test
    public void testGetCorpusProcessingState() {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put(ConceptInsights.ACCOUNT_ID, "public");
        params.put(ConceptInsights.CORPUS, "ibmresearcher");
        CorpusProcessingState corpusProcessingState = service.getCorpusProcessingState("public","ibmresearcher");
        Assert.assertNotNull(corpusProcessingState);
    }