@Test
    public void testOnCreateOrUpdateApplication() throws GenieException {
        this.a = new ApplicationEntity(NAME, USER, VERSION, ApplicationStatus.ACTIVE);
        Assert.assertNotNull(this.a.getTags());
        this.a.onCreateOrUpdateApplication();
        Assert.assertEquals(2, this.a.getTags().size());
    }