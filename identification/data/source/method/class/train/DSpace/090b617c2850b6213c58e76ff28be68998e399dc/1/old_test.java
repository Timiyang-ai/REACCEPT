@Test
    public void testCrosswalkMetadata()
            throws Exception
    {
        System.out.println("crosswalkMetadata");

        // Set up the instance to be tested
        EZIDIdentifierProvider instance = new EZIDIdentifierProvider();
        instance.setConfigurationService(config);
        instance.setCrosswalk(aCrosswalk);
        instance.setCrosswalkTransform(crosswalkTransforms);

        // Let's have a fresh Item to work with
        DSpaceObject dso = newItem(context);
        String handle = dso.getHandle();

        // Test!
        Map<String, String> metadata = instance.crosswalkMetadata(dso);

        // Evaluate
        String target = (String) metadata.get("_target");
        assertEquals("Generates correct _target metadatum",
                config.getProperty("dspace.url") + "/handle/" + handle,
                target);
        assertTrue("Has title", metadata.containsKey("datacite.title"));
        assertTrue("Has publication year", metadata.containsKey("datacite.publicationyear"));
        assertTrue("Has publisher", metadata.containsKey("datacite.publisher"));
        assertTrue("Has creator", metadata.containsKey("datacite.creator"));

        // Dump out the generated metadata for inspection
        System.out.println("Results:");
        for (Entry metadatum : metadata.entrySet())
        {
            System.out.printf("  %s : %s\n", metadatum.getKey(), metadatum.getValue());
        }
    }