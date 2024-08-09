@Test
    public void testOnCreateOrUpdateCommonEntityFields() throws GeniePreconditionException {
        this.c = new CommonEntityFields(NAME, USER, VERSION);
        this.c.onCreateOrUpdateCommonEntityFields();
    }