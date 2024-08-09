@Test
  public void createFileTest() throws Exception {
    // created nested file
    mTree.createPath(NESTED_FILE_URI, sNestedFileOptions);
    Inode<?> nestedFile = mTree.getInodeByPath(NESTED_FILE_URI);
    Assert.assertEquals("file", nestedFile.getName());
    Assert.assertEquals(2, nestedFile.getParentId());
    Assert.assertTrue(nestedFile.isFile());
    Assert.assertEquals("user1", nestedFile.getUserName());
    Assert.assertTrue(nestedFile.getGroupName().isEmpty());
    Assert.assertEquals((short) 0644, nestedFile.getPermission());
  }