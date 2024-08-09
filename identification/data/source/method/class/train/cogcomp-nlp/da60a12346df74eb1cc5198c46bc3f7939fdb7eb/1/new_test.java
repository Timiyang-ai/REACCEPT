    @Test
    public void annotate() throws Exception {
        Preprocessor preprocessor = new Preprocessor();
        String[] sentTokens = {"<root>", "This", "is", "a", "test", "sentence"};
        TextAnnotation ta = preprocessor.annotate("testCorpus", "testSent1", sentTokens);
        assertNotNull(ta);
        assertTrue(ta.hasView(ViewNames.POS));
        assertTrue(ta.hasView(ViewNames.LEMMA));
        assertTrue(ta.hasView(ViewNames.SHALLOW_PARSE));
        assertEquals("NN", ta.getView(ViewNames.POS).getLabelsCoveringToken(4).get(0));
    }