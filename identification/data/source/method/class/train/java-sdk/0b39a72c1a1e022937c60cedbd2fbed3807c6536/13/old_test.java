@Test
    public void testGetGraphsRelatedConcepts() {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put(ConceptInsights.ACCOUNT_ID, "wikipedia");
        params.put("graph", "en-20120601");
        List<String> concepts = new ArrayList<String>();
        concepts.add("/graphs/wikipedia/en-20120601/concepts/IBM_Watson");
        params.put(ConceptInsights.CONCEPTS, concepts);
        params.put(ConceptInsights.LIMIT, 10);
        params.put(ConceptInsights.LEVEL, 1);
        RequestedFields fs = new RequestedFields();
        fs.include("abstract");
        params.put("concept_fields", fs);
        Concepts concepts1 =  service.getGraphsRelatedConcepts(params);
        Assert.assertNotNull(concepts1);
    }