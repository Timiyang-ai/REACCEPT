@Test
  public void lockReadAndCheckNameAndParent() throws Exception {
    String name = "file";
    InodeFile inode1 = createInodeFile(1);
    InodeDirectory dir1 = createInodeDirectory();
    inode1.setName(name);
    inode1.setParentId(dir1.getId());
    inode1.lockReadAndCheckNameAndParent(dir1, name);
    Assert.assertTrue(inode1.isReadLocked());
    inode1.unlockRead();
  }