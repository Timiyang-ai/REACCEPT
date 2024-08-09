@Test
  public void getPathTest() throws Exception {
    Inode root = mTree.getInodeById(0);
    // test root path
    Assert.assertEquals(new AlluxioURI("/"), mTree.getPath(root));

    // test one level
    InodeTree.CreatePathResult createResult = mTree.createPath(TEST_URI, sDirectoryOptions);
    List<Inode> created = createResult.getCreated();
    Assert.assertEquals(new AlluxioURI("/test"), mTree.getPath(created.get(created.size() - 1)));

    // test nesting
    createResult = mTree.createPath(NESTED_URI, sNestedDirectoryOptions);
    created = createResult.getCreated();
    Assert.assertEquals(new AlluxioURI("/nested/test"),
        mTree.getPath(created.get(created.size() - 1)));
  }