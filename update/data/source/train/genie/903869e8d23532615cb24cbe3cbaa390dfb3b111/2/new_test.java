@Test
    public void testGetTagsForApplication() throws GenieException {
        Assert.assertEquals(1, this.appService.getTagsForApplication(APP_1_ID).size());
    }