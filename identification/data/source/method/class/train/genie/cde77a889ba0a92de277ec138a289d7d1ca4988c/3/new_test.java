@Test
    public void testSetApplications() throws GeniePreconditionException {
        Assert.assertNotNull(this.c.getApplications());
        Assert.assertTrue(this.c.getApplications().isEmpty());
        final Set<Application> applications = new HashSet<>();
        final Application one = new Application();
        one.setId("one");
        final Application two = new Application();
        two.setId("two");
        applications.add(one);
        applications.add(two);
        this.c.setApplications(applications);
        Assert.assertEquals(2, this.c.getApplications().size());
        Assert.assertTrue(this.c.getApplications().contains(one));
        Assert.assertTrue(this.c.getApplications().contains(two));
        Assert.assertTrue(one.getCommands().contains(this.c));
        Assert.assertTrue(two.getCommands().contains(this.c));

        applications.clear();
        applications.add(two);
        this.c.setApplications(applications);
        Assert.assertEquals(1, this.c.getApplications().size());
        Assert.assertTrue(this.c.getApplications().contains(two));
        Assert.assertFalse(one.getCommands().contains(this.c));
        Assert.assertTrue(two.getCommands().contains(this.c));
        this.c.setApplications(null);
        Assert.assertTrue(this.c.getApplications().isEmpty());
        Assert.assertTrue(one.getCommands().isEmpty());
        Assert.assertTrue(two.getCommands().isEmpty());
    }