@Test
    public void testMint()
            throws Exception
    {
        System.out.println("mint");

        DataCiteIdentifierProvider instance
                = (DataCiteIdentifierProvider)
                sm.getServicesByType(DataCiteIdentifierProvider.class).get(0);

        DSpaceObject dso = newItem(context);
        String result = instance.mint(context, dso);
        assertNotNull("Null returned", result);
    }