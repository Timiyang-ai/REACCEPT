@Test
  public void lockWriteAndCheckNameAndParent() throws Exception {
    String name = "file";
    InodeFile inode1 = createInodeFile(1);
    InodeDirectory dir1 = createInodeDirectory();
    inode1.setName(name);
    inode1.setParentId(dir1.getId());
    inode1.lockWriteAndCheckNameAndParent(dir1, name);
    Assert.assertTrue(inode1.isWriteLocked());
    inode1.unlockWrite();
  }