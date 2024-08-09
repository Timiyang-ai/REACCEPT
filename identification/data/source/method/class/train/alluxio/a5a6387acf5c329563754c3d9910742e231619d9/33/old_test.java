  @Test
  public void createPathTest() throws Exception {
    // save the last mod time of the root
    long lastModTime = mTree.getRoot().getLastModificationTimeMs();
    // sleep to ensure a different last modification time
    CommonUtils.sleepMs(10);

    // Need to use updated options to set the correct last mod time.
    CreateDirectoryContext dirContext = CreateDirectoryContext.mergeFrom(
        CreateDirectoryPOptions.newBuilder().setRecursive(true).setMode(TEST_DIR_MODE.toProto()))
            .setOwner(TEST_OWNER).setGroup(TEST_GROUP);

    // create nested directory
    List<Inode> created = createPath(mTree, NESTED_URI, dirContext);
    // 1 modified directory
    assertNotEquals(lastModTime,
        getInodeByPath(NESTED_URI.getParent()).getLastModificationTimeMs());
    // 2 created directories
    assertEquals(2, created.size());
    assertEquals("nested", created.get(0).getName());
    assertEquals("test", created.get(1).getName());
    // save the last mod time of 'test'
    lastModTime = created.get(1).getLastModificationTimeMs();
    // sleep to ensure a different last modification time
    CommonUtils.sleepMs(10);

    // creating the directory path again results in no new inodes.
    try {
      createPath(mTree, NESTED_URI, dirContext);
      assertTrue("createPath should throw FileAlreadyExistsException", false);
    } catch (FileAlreadyExistsException e) {
      assertEquals(e.getMessage(),
          ExceptionMessage.FILE_ALREADY_EXISTS.getMessage(NESTED_URI));
    }

    // create a file
    CreateFileContext options = CreateFileContext.mergeFrom(
        CreateFilePOptions.newBuilder().setBlockSizeBytes(Constants.KB).setRecursive(true));
    created = createPath(mTree, NESTED_FILE_URI, options);

    // test directory was modified
    assertNotEquals(lastModTime, getInodeByPath(NESTED_URI).getLastModificationTimeMs());
    // file was created
    assertEquals(1, created.size());
    assertEquals("file", created.get(0).getName());
  }