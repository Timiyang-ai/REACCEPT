@Test
    public void testGetConcept() {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put(ConceptInsights.ACCOUNT_ID, "wikipedia");
        params.put("graph", "en-20120601");
        params.put("concept", "IBM");
        ConceptMetadata conceptMetaData =  service.getConcept(params);
        Assert.assertNotNull(conceptMetaData);

    }