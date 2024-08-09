@Test
    public void testGetCorpusRelationScores() {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put(ConceptInsights.ACCOUNT_ID, "public");
        params.put(ConceptInsights.CORPUS, "ibmresearcher");
        List<String> concepts = new ArrayList<String>();
        concepts.add("/graphs/wikipedia/en-20120601/concepts/IBM_Watson");
        params.put(ConceptInsights.CONCEPTS, concepts);
        params.put(ConceptInsights.CONCEPT, "IBM");
        Scores scores = service.getCorpusRelationScores(params);
        Assert.assertNotNull(scores);
    }