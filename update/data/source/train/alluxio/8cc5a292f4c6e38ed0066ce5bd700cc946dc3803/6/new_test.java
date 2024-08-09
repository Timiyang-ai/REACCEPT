@Test
  public void createFile() throws Exception {
    // created nested file
    createPath(mTree, NESTED_FILE_URI, sNestedFileContext);
    InodeView nestedFile = getInodeByPath(mTree, NESTED_FILE_URI);
    assertEquals("file", nestedFile.getName());
    assertEquals(2, nestedFile.getParentId());
    assertTrue(nestedFile.isFile());
    assertEquals("user1", nestedFile.getOwner());
    assertEquals("group1", nestedFile.getGroup());
    assertEquals(TEST_FILE_MODE.toShort(), nestedFile.getMode());
  }