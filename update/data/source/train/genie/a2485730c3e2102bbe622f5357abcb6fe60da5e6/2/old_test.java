@Test
    public void testSetApplications() throws GeniePreconditionException {
        Assert.assertNotNull(this.c.getApplications());
        Assert.assertTrue(this.c.getApplications().isEmpty());
        final Set<ApplicationEntity> applicationEntities = new HashSet<>();
        final ApplicationEntity one = new ApplicationEntity();
        one.setId("one");
        final ApplicationEntity two = new ApplicationEntity();
        two.setId("two");
        applicationEntities.add(one);
        applicationEntities.add(two);
        this.c.setApplications(applicationEntities);
        Assert.assertEquals(2, this.c.getApplications().size());
        Assert.assertTrue(this.c.getApplications().contains(one));
        Assert.assertTrue(this.c.getApplications().contains(two));
        Assert.assertTrue(one.getCommands().contains(this.c));
        Assert.assertTrue(two.getCommands().contains(this.c));

        applicationEntities.clear();
        applicationEntities.add(two);
        this.c.setApplications(applicationEntities);
        Assert.assertEquals(1, this.c.getApplications().size());
        Assert.assertTrue(this.c.getApplications().contains(two));
        Assert.assertFalse(one.getCommands().contains(this.c));
        Assert.assertTrue(two.getCommands().contains(this.c));
        this.c.setApplications(null);
        Assert.assertTrue(this.c.getApplications().isEmpty());
        Assert.assertTrue(one.getCommands().isEmpty());
        Assert.assertTrue(two.getCommands().isEmpty());
    }