@Test
    public void testAnalyze() throws IOException {
        Document doc = new Document();
        StringWriter xrefOut = new StringWriter();
        analyzer.analyze(doc, new StreamSource() {
            @Override
            public InputStream getStream() throws IOException {
                return new ByteArrayInputStream(content.getBytes());
            }
        }, xrefOut);
    }