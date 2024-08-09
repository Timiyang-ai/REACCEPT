@Test
    public void testSetApplication() throws GeniePreconditionException {
        Assert.assertNull(this.c.getApplication());
        final Application one = new Application();
        one.setId("one");
        final Application two = new Application();
        two.setId("two");
        this.c.setApplication(one);
        Assert.assertEquals(one, this.c.getApplication());
        Assert.assertTrue(one.getCommands().contains(this.c));
        this.c.setApplication(two);
        Assert.assertEquals(two, this.c.getApplication());
        Assert.assertFalse(one.getCommands().contains(this.c));
        Assert.assertTrue(two.getCommands().contains(this.c));
        this.c.setApplication(null);
        Assert.assertNull(this.c.getApplication());
        Assert.assertTrue(one.getCommands().isEmpty());
        Assert.assertTrue(two.getCommands().isEmpty());
    }