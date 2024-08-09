@Test
    public void testAnalyze() throws IOException {
        Document doc = new Document();
        analyzer.analyze(doc, new ByteArrayInputStream(content.getBytes()));
    }