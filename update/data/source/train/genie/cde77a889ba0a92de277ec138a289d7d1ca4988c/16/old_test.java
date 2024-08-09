@Test
    @DatabaseSetup("testMarkZombies.xml")
    public void testMarkZombies() throws Exception {
        Assert.assertEquals(2, this.janitor.markZombies());
    }