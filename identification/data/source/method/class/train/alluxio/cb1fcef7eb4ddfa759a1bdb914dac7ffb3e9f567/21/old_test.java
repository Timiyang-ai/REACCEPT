  @Test
  public void propagatePersisted() throws Exception {
    AlluxioURI nestedFile = new AlluxioURI("/nested1/nested2/file");
    AlluxioURI parent1 = new AlluxioURI("/nested1/");
    AlluxioURI parent2 = new AlluxioURI("/nested1/nested2/");

    createFileWithSingleBlock(nestedFile);

    // Nothing is persisted yet.
    assertEquals(PersistenceState.NOT_PERSISTED.toString(),
        mFileSystemMaster.getFileInfo(nestedFile, GetStatusContext.defaults())
            .getPersistenceState());
    assertEquals(PersistenceState.NOT_PERSISTED.toString(),
        mFileSystemMaster.getFileInfo(parent1,
            GetStatusContext.defaults()).getPersistenceState());
    assertEquals(PersistenceState.NOT_PERSISTED.toString(),
        mFileSystemMaster.getFileInfo(parent2,
            GetStatusContext.defaults()).getPersistenceState());

    // Persist the file.
    mFileSystemMaster.setAttribute(nestedFile,
        SetAttributeContext.mergeFrom(SetAttributePOptions.newBuilder().setPersisted(true)));

    // Everything component should be persisted.
    assertEquals(PersistenceState.PERSISTED.toString(),
        mFileSystemMaster.getFileInfo(nestedFile, GetStatusContext.defaults())
            .getPersistenceState());
    assertEquals(PersistenceState.PERSISTED.toString(),
        mFileSystemMaster.getFileInfo(parent1,
            GetStatusContext.defaults()).getPersistenceState());
    assertEquals(PersistenceState.PERSISTED.toString(),
        mFileSystemMaster.getFileInfo(parent2,
            GetStatusContext.defaults()).getPersistenceState());

    // Simulate restart.
    stopServices();
    startServices();

    // Everything component should be persisted.
    assertEquals(PersistenceState.PERSISTED.toString(),
        mFileSystemMaster.getFileInfo(nestedFile, GetStatusContext.defaults())
            .getPersistenceState());
    assertEquals(PersistenceState.PERSISTED.toString(),
        mFileSystemMaster.getFileInfo(parent1,
            GetStatusContext.defaults()).getPersistenceState());
    assertEquals(PersistenceState.PERSISTED.toString(),
        mFileSystemMaster.getFileInfo(parent2,
            GetStatusContext.defaults()).getPersistenceState());
  }