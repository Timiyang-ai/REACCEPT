  @Test
  public void inodePathExists() throws Exception {
    assertFalse(mTree.inodePathExists(TEST_URI));

    createPath(mTree, TEST_URI, sFileContext);
    assertTrue(mTree.inodePathExists(TEST_URI));

    deleteInodeByPath(mTree, TEST_URI);
    assertFalse(mTree.inodePathExists(TEST_URI));
  }