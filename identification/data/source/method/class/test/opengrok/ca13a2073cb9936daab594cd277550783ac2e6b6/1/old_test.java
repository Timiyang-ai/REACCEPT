    @Test
    public void addExtension() throws Exception {
        // should not find analyzer for this unlikely extension
        assertNull(AnalyzerGuru.find("file.unlikely_extension"));

        AnalyzerFactory
                faf = AnalyzerGuru.findFactory(ShAnalyzerFactory.class.getName());
        // should be the same factory as the built-in analyzer for sh scripts
        assertSame(AnalyzerGuru.find("myscript.sh"), faf);

        // add an analyzer for the extension and see that it is picked up
        AnalyzerGuru.addExtension("UNLIKELY_EXTENSION", faf);
        assertSame(ShAnalyzerFactory.class,
                   AnalyzerGuru.find("file.unlikely_extension").getClass());

        // remove the mapping and verify that it is gone
        AnalyzerGuru.addExtension("UNLIKELY_EXTENSION", null);
        assertNull(AnalyzerGuru.find("file.unlikely_extension"));
    }