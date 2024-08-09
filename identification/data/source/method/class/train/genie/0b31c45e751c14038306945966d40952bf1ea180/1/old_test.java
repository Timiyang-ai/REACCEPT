@Test
    public void testValidate() throws GenieException {
        this.a = new Application(NAME, USER, ApplicationStatus.ACTIVE, VERSION);
        this.a.validate();
    }