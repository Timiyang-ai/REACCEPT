  @Test
  public void completeFileDefaultsTest() {
    CompleteFilePOptions options = FileSystemMasterOptions.completeFileDefaults();
    Assert.assertNotNull(options);
    Assert.assertEquals(0, options.getUfsLength());
  }