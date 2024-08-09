@Test
  public void inodeIdExists() throws Exception {
    assertTrue(mTree.inodeIdExists(0));
    assertFalse(mTree.inodeIdExists(1));

    createPath(mTree, TEST_URI, sFileOptions);
    Inode<?> inode = getInodeByPath(mTree, TEST_URI);
    assertTrue(mTree.inodeIdExists(inode.getId()));

    deleteInodeByPath(mTree, TEST_URI);
    assertFalse(mTree.inodeIdExists(inode.getId()));
  }