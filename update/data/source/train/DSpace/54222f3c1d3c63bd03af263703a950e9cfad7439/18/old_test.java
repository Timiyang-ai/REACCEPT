@Test
    public void testGetMatches() throws IOException, ClassNotFoundException
    {
        System.out.println("getMatches");

        final String PLUGIN_INTERFACE = "org.dspace.content.authority.ChoiceAuthority";

        // Ensure that 'id' attribute is optional
        String field = null; // not used
        String text = "north 40";
        int collection = 0;
        int start = 0;
        int limit = 0;
        String locale = null;
        // This "farm" Controlled Vocab is included in TestEnvironment data 
        // (under /src/test/data/dspaceFolder/) and it should be auto-loaded
        // by test configs in /src/test/data/dspace.cfg.more
        DSpaceControlledVocabulary instance = (DSpaceControlledVocabulary)
                PluginManager.getNamedPlugin(Class.forName(PLUGIN_INTERFACE), "farm");
        assertNotNull(instance);
        Choices result = instance.getMatches(field, text, collection, start,
                limit, locale);
        assertEquals("the farm::north 40", result.values[0].value);
    }