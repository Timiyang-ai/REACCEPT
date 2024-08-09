@Test
  public void testSetupWorkingDirectory() throws Exception {
    boolean isVerbose = true;

    PowerMockito.mockStatic(FileUtils.class);
    // work directory not exist
    PowerMockito.when(FileUtils.isDirectoryExists(Mockito.anyString())).thenReturn(false);

    // Failed to create dir
    PowerMockito.when(FileUtils.createDirectory(Mockito.anyString())).thenReturn(false);
    Assert.assertFalse(SchedulerUtils.createOrCleanDirectory(WORKING_DIR));
    // OK to create dir
    PowerMockito.when(FileUtils.createDirectory(Mockito.anyString())).thenReturn(true);

    // Fail to cleanup
    PowerMockito.when(FileUtils.cleanDir(Mockito.anyString())).thenReturn(false);
    Assert.assertFalse(SchedulerUtils.createOrCleanDirectory(WORKING_DIR));

    // Ok to cleanup
    PowerMockito.when(FileUtils.cleanDir(Mockito.anyString())).thenReturn(true);
    Assert.assertTrue(SchedulerUtils.createOrCleanDirectory(WORKING_DIR));
  }