@Test
    public void testAnnotateText() {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put(ConceptInsights.ACCOUNT_ID, "wikipedia");
        params.put("graph", "en-20120601");
        params.put("text", "Nizar Magboul Alseddeg is currently living in Austin Texas");
        Annotations  annotations= service.annotateText(params);
        Assert.assertNotNull(annotations);
    }