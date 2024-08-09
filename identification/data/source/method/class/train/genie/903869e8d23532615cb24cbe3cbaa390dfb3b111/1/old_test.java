@Test
    public void testGetTagsForApplication() throws GenieException {
        Assert.assertEquals(3,
            this.appService.getTagsForApplication(APP_1_ID).size());
    }