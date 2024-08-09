    @Test
    public void addPrefix() throws Exception {
        // should not find analyzer for this unlikely extension
        assertNull(AnalyzerGuru.find("unlikely_prefix.foo"));

        AnalyzerFactory
                faf = AnalyzerGuru.findFactory(ShAnalyzerFactory.class.getName());
        // should be the same factory as the built-in analyzer for sh scripts
        assertSame(AnalyzerGuru.find("myscript.sh"), faf);

        // add an analyzer for the prefix and see that it is picked up
        AnalyzerGuru.addPrefix("UNLIKELY_PREFIX", faf);
        assertSame(ShAnalyzerFactory.class,
                   AnalyzerGuru.find("unlikely_prefix.foo").getClass());

        // remove the mapping and verify that it is gone
        AnalyzerGuru.addPrefix("UNLIKELY_PREFIX", null);
        assertNull(AnalyzerGuru.find("unlikely_prefix.foo"));
    }