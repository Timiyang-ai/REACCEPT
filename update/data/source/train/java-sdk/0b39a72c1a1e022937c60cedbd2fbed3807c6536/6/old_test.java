@Test
    public void testGetDocument() {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put(ConceptInsights.ACCOUNT_ID, "public");
        params.put(ConceptInsights.CORPUS, "ibmresearcher");
        params.put(ConceptInsights.DOCUMENT, "il-AHARONA");
        Document document = service.getDocument("public", "ibmresearcher", "il-AHARONA");
        Assert.assertNotNull(document);
    }