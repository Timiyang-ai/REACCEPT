@Test
    public void testValidate() throws GeniePreconditionException {
        this.a = new Application(NAME, USER, ApplicationStatus.ACTIVE, VERSION);
        this.a.validate();
    }