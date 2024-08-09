@Test
  public void createDirectory() throws Exception {
    // create directory
    createPath(mTree, TEST_URI, sDirectoryOptions);
    assertTrue(mTree.inodePathExists(TEST_URI));
    InodeView test = getInodeByPath(mTree, TEST_URI);
    assertEquals(TEST_PATH, test.getName());
    assertTrue(test.isDirectory());
    assertEquals("user1", test.getOwner());
    assertEquals("group1", test.getGroup());
    assertEquals(TEST_DIR_MODE.toShort(), test.getMode());

    // create nested directory
    createPath(mTree, NESTED_URI, sNestedDirectoryOptions);
    assertTrue(mTree.inodePathExists(NESTED_URI));
    InodeView nested = getInodeByPath(mTree, NESTED_URI);
    assertEquals(TEST_PATH, nested.getName());
    assertEquals(2, nested.getParentId());
    assertTrue(test.isDirectory());
    assertEquals("user1", test.getOwner());
    assertEquals("group1", test.getGroup());
    assertEquals(TEST_DIR_MODE.toShort(), test.getMode());
  }