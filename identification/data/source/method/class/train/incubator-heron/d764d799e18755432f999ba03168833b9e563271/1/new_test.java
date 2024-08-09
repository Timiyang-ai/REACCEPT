@Test
  public void testSetupWorkingDirectory() throws Exception {
    boolean isVerbose = true;

    PowerMockito.mockStatic(FileUtils.class);
    // work directory not exist
    PowerMockito.when(FileUtils.isDirectoryExists(Mockito.anyString())).thenReturn(false);

    // Failed to create dir
    PowerMockito.when(FileUtils.createDirectory(Mockito.anyString())).thenReturn(false);
    Assert.assertFalse(SchedulerUtils.setupWorkingDirectory(
            WORKING_DIR, CORE_RELEASE_URI, CORE_RELEASE_DEST,
            TOPOLOGY_URI, TOPOLOGY_DEST, isVerbose));

    // OK to create dir
    PowerMockito.when(FileUtils.createDirectory(Mockito.anyString())).thenReturn(true);

    // Fail to cleanup
    PowerMockito.when(FileUtils.cleanDir(Mockito.anyString())).thenReturn(false);
    Assert.assertFalse(SchedulerUtils.setupWorkingDirectory(
            WORKING_DIR, CORE_RELEASE_URI, CORE_RELEASE_DEST,
            TOPOLOGY_URI, TOPOLOGY_DEST, isVerbose));

    // Ok to cleanup
    PowerMockito.when(FileUtils.cleanDir(Mockito.anyString())).thenReturn(true);

    PowerMockito.spy(SchedulerUtils.class);
    // OK to curl and extract core-release-package
    PowerMockito.doReturn(true).when(SchedulerUtils.class, "curlAndExtractPackage",
        Mockito.eq(WORKING_DIR), Mockito.eq(CORE_RELEASE_URI),
        Mockito.eq(CORE_RELEASE_DEST), Mockito.eq(true), Mockito.eq(isVerbose));

    // Failed to curl and extract topology-package
    PowerMockito.doReturn(false).when(SchedulerUtils.class, "curlAndExtractPackage",
        Mockito.eq(WORKING_DIR), Mockito.eq(TOPOLOGY_URI),
        Mockito.eq(TOPOLOGY_DEST), Mockito.anyBoolean(), Mockito.anyBoolean());
    Assert.assertFalse(SchedulerUtils.setupWorkingDirectory(
            WORKING_DIR, CORE_RELEASE_URI, CORE_RELEASE_DEST,
            TOPOLOGY_URI, TOPOLOGY_DEST, isVerbose));

    // OK to curl and extract topology-package
    PowerMockito.doReturn(true).when(SchedulerUtils.class, "curlAndExtractPackage",
        Mockito.eq(WORKING_DIR), Mockito.eq(TOPOLOGY_URI),
        Mockito.eq(TOPOLOGY_DEST), Mockito.anyBoolean(), Mockito.anyBoolean());
    Assert.assertTrue(SchedulerUtils.setupWorkingDirectory(
            WORKING_DIR, CORE_RELEASE_URI, CORE_RELEASE_DEST,
            TOPOLOGY_URI, TOPOLOGY_DEST, isVerbose));
  }