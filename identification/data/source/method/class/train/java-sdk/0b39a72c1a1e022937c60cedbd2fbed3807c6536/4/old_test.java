@Test
    public void testGetCorpusStats() {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put(ConceptInsights.ACCOUNT_ID, "public");
        params.put(ConceptInsights.CORPUS, "ibmresearcher");
        CorpusStats corpusStats = service.getCorpusStats("public", "ibmresearcher");
        Assert.assertNotNull(corpusStats);
    }