@Test
    public void testLookup()
            throws Exception
    {
        System.out.println("lookup");

        DataCiteIdentifierProvider instance
                = (DataCiteIdentifierProvider)
                sm.getServicesByType(DataCiteIdentifierProvider.class).get(0);

        DSpaceObject object = item;
        String result = instance.lookup(context, object);
        assertNotNull("Null returned", result);
    }