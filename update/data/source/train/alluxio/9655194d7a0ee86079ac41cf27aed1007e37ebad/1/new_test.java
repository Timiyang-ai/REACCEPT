@Test
  public void getPathTest() throws Exception {
    try (LockedInodePath inodePath = mTree.lockFullInodePath(0, InodeTree.LockMode.READ)) {
      Inode<?> root = inodePath.getInode();
      // test root path
      Assert.assertEquals(new AlluxioURI("/"), mTree.getPath(root));
    }

    // test one level
    InodeTree.CreatePathResult createResult = createPath(mTree, TEST_URI, sDirectoryOptions);
    List<Inode<?>> created = createResult.getCreated();
    Assert.assertEquals(new AlluxioURI("/test"), mTree.getPath(created.get(created.size() - 1)));

    // test nesting
    createResult = createPath(mTree, NESTED_URI, sNestedDirectoryOptions);
    created = createResult.getCreated();
    Assert.assertEquals(new AlluxioURI("/nested/test"),
        mTree.getPath(created.get(created.size() - 1)));
  }