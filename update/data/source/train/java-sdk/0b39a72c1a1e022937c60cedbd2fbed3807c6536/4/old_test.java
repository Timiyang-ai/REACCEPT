@Test
    public void testConceptualSearch() {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put(ConceptInsights.ACCOUNT_ID, "public");
        params.put(ConceptInsights.CORPUS, "ibmresearcher");
        List<String> ids = new ArrayList<String>();
        ids.add("/graphs/wikipedia/en-20120601/concepts/IBM_Watson");
        params.put(ConceptInsights.IDS, ids);
        params.put(ConceptInsights.LIMIT, 10);
        params.put(ConceptInsights.CURSOR, 0);
        QueryConcepts cp =  service.conceptualSearch(params);
        Assert.assertNotNull(cp);
    }