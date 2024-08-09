@Test
    public void testGetDocumentRelationScores() {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put(ConceptInsights.ACCOUNT_ID, "public");
        params.put(ConceptInsights.CORPUS, "ibmresearcher");
        params.put(ConceptInsights.DOCUMENT, "il-AHARONA");
        List<String> concepts = new ArrayList<String>();
        concepts.add("/graphs/wikipedia/en-20120601/concepts/IBM_Watson");
        params.put(ConceptInsights.CONCEPTS, concepts);
        params.put(ConceptInsights.CONCEPT_ID, "IBM");
        Scores scores = service.getDocumentRelationScores(params);
        Assert.assertNotNull(scores);
    }