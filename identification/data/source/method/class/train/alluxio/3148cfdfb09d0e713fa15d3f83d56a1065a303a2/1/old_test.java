  @Test
  public void getPath() throws Exception {
    try (LockedInodePath inodePath = mTree.lockFullInodePath(0, LockPattern.READ)) {
      Inode root = inodePath.getInode();
      // test root path
      assertEquals(new AlluxioURI("/"), mTree.getPath(root));
    }

    // test one level
    createPath(mTree, TEST_URI, sDirectoryContext);
    try (LockedInodePath inodePath = mTree.lockFullInodePath(TEST_URI, LockPattern.READ)) {
      assertEquals(new AlluxioURI("/test"), mTree.getPath(inodePath.getInode()));
    }

    // test nesting
    createPath(mTree, NESTED_URI, sNestedDirectoryContext);
    try (LockedInodePath inodePath = mTree.lockFullInodePath(NESTED_URI, LockPattern.READ)) {
      assertEquals(new AlluxioURI("/nested/test"), mTree.getPath(inodePath.getInode()));
    }
  }