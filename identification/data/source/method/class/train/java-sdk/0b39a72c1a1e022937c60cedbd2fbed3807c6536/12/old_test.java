@Test
    public void testGetCorpus() {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put(ConceptInsights.ACCOUNT_ID, "public");
        params.put(ConceptInsights.CORPUS, "TEDTalks");

        Corpus corpus= service.getCorpus("public", "TEDTalks");
        Assert.assertNotNull(corpus);
    }