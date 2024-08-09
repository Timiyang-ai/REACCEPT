@Test
    public void testSupports_Class()
    {
        System.out.println("supports Class");

        EZIDIdentifierProvider instance = new DSpace().getServiceManager().getServiceByName(EZIDIdentifierProvider.class.getName(), EZIDIdentifierProvider.class);

        Class<? extends Identifier> identifier = DOI.class;
        boolean result = instance.supports(identifier);
        assertTrue("DOI is supported", result);
    }