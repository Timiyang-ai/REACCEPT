@Test
  public void testGetSummary() {
    final File images = new File("src/test/resources/images.zip");
    final Summary summary = service.getSummary(images);
    Assert.assertNotNull(summary);
  }