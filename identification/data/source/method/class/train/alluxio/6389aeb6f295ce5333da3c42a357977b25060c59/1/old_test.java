  @Test
  public void deleteInode() throws Exception {
    createPath(mTree, NESTED_URI, sNestedDirectoryContext);

    // all inodes under root
    try (LockedInodePath inodePath = mTree.lockFullInodePath(0, LockPattern.WRITE_INODE)) {
      // /nested, /nested/test
      assertEquals(2, mTree.getDescendants(inodePath).getInodePathList().size());

      // delete the nested inode
      deleteInodeByPath(mTree, NESTED_URI);

      // only /nested left
      assertEquals(1, mTree.getDescendants(inodePath).getInodePathList().size());
    }
  }