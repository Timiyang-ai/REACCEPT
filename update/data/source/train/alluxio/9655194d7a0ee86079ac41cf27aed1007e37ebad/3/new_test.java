@Test
  public void createDirectoryTest() throws Exception {
    // create directory
    createPath(mTree, TEST_URI, sDirectoryOptions);
    Assert.assertTrue(mTree.inodePathExists(TEST_URI));
    Inode<?> test = getInodeByPath(mTree, TEST_URI);
    Assert.assertEquals(TEST_PATH, test.getName());
    Assert.assertTrue(test.isDirectory());
    Assert.assertEquals("user1", test.getUserName());
    Assert.assertTrue(test.getGroupName().isEmpty());
    Assert.assertEquals((short) 0755, test.getPermission());

    // create nested directory
    createPath(mTree, NESTED_URI, sNestedDirectoryOptions);
    Assert.assertTrue(mTree.inodePathExists(NESTED_URI));
    Inode<?> nested = getInodeByPath(mTree, NESTED_URI);
    Assert.assertEquals(TEST_PATH, nested.getName());
    Assert.assertEquals(2, nested.getParentId());
    Assert.assertTrue(test.isDirectory());
    Assert.assertEquals("user1", test.getUserName());
    Assert.assertTrue(test.getGroupName().isEmpty());
    Assert.assertEquals((short) 0755, test.getPermission());
  }