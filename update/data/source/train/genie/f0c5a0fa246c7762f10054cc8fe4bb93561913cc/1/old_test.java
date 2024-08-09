@Test
    public void testOnCreateOrUpdateApplication() throws GeniePreconditionException {
        this.a = new Application(NAME, USER, VERSION, ApplicationStatus.ACTIVE);
        Assert.assertNull(this.a.getTags());
        this.a.onCreateOrUpdateApplication();
        Assert.assertEquals(2, this.a.getTags().size());
    }