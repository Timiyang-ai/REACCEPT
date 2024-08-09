@Test
  public void getPath() throws Exception {
    try (LockedInodePath inodePath = mTree.lockFullInodePath(0, InodeTree.LockMode.READ)) {
      Inode<?> root = inodePath.getInode();
      // test root path
      assertEquals(new AlluxioURI("/"), mTree.getPath(root));
    }

    // test one level
    createPath(mTree, TEST_URI, sDirectoryOptions);
    try (LockedInodePath inodePath = mTree.lockFullInodePath(TEST_URI, InodeTree.LockMode.READ)) {
      assertEquals(new AlluxioURI("/test"), mTree.getPath(inodePath.getInode()));
    }

    // test nesting
    createPath(mTree, NESTED_URI, sNestedDirectoryOptions);
    try (LockedInodePath inodePath = mTree.lockFullInodePath(NESTED_URI, InodeTree.LockMode.READ)) {
      assertEquals(new AlluxioURI("/nested/test"), mTree.getPath(inodePath.getInode()));
    }
  }