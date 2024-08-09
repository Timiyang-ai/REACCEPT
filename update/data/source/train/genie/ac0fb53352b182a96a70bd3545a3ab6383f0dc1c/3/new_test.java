@Test
    public void testOnCreateOrUpdateCommand() throws GenieException {
        this.c = new CommandEntity(NAME, USER, VERSION, CommandStatus.ACTIVE, EXECUTABLE);
        Assert.assertNotNull(this.c.getTags());
        Assert.assertTrue(this.c.getTags().isEmpty());
        this.c.onCreateOrUpdateCommand();
        Assert.assertEquals(2, this.c.getTags().size());
    }