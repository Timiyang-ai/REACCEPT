@Test
    public void testGetSummary() {
        File images = new File("src/test/resources/images.zip");
        Summary summary = service.getSummary(images);
        Assert.assertNotNull(summary);
    }