@Test
    public void testOnCreateOrUpdateCommonEntityFields() throws GenieException {
        this.c = new CommonEntityFields(NAME, USER, VERSION);
        this.c.onCreateOrUpdateCommonEntityFields();
    }