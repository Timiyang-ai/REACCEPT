@Test
  public void createPathTest() throws Exception {
    // save the last mod time of the root
    long lastModTime = mTree.getRoot().getLastModificationTimeMs();
    // sleep to ensure a different last modification time
    CommonUtils.sleepMs(10);

    // Need to use updated options to set the correct last mod time.
    CreateDirectoryOptions dirOptions = CreateDirectoryOptions.defaults().setOwner(TEST_OWNER)
        .setGroup(TEST_GROUP).setMode(TEST_DIR_MODE).setRecursive(true);

    // create nested directory
    InodeTree.CreatePathResult createResult = createPath(mTree, NESTED_URI, dirOptions);
    List<Inode<?>> modified = createResult.getModified();
    List<Inode<?>> created = createResult.getCreated();

    // 1 modified directory
    assertEquals(1, modified.size());
    assertEquals("", modified.get(0).getName());
    assertNotEquals(lastModTime, modified.get(0).getLastModificationTimeMs());
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
      createPath(mTree, NESTED_URI, dirOptions);
      assertTrue("createPath should throw FileAlreadyExistsException", false);
    } catch (FileAlreadyExistsException e) {
      assertEquals(e.getMessage(),
          ExceptionMessage.FILE_ALREADY_EXISTS.getMessage(NESTED_URI));
    }

    // create a file
    CreateFileOptions options =
        CreateFileOptions.defaults().setBlockSizeBytes(Constants.KB).setRecursive(true);
    createResult = createPath(mTree, NESTED_FILE_URI, options);
    modified = createResult.getModified();
    created = createResult.getCreated();

    // test directory was modified
    assertEquals(1, modified.size());
    assertEquals("test", modified.get(0).getName());
    assertNotEquals(lastModTime, modified.get(0).getLastModificationTimeMs());
    // file was created
    assertEquals(1, created.size());
    assertEquals("file", created.get(0).getName());
  }