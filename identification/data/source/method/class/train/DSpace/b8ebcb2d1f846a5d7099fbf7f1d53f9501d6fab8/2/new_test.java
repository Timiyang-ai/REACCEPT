@Test
    public void testLookup()
            throws Exception
    {
        System.out.println("lookup");

        DataCiteIdentifierProvider instance
                = (DataCiteIdentifierProvider)
                sm.getServicesByType(DataCiteIdentifierProvider.class).get(0);

        String identifier = UUID.randomUUID().toString();
        DSpaceObject object = newItem(context);
        instance.register(context, object, identifier);

        String result = instance.lookup(context, object);
        assertNotNull("Null returned", result);
    }