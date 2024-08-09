  @Test
  public void getMutable() {
    writeInode(mRoot);
    assertEquals(mRoot, mStore.getMutable(0).get());
  }