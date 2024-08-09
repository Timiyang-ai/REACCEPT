  @Test
  public void toStringEntries() throws IOException {
    RocksInodeStore store = new RocksInodeStore(mFolder.newFolder().getAbsolutePath());
    assertEquals("", store.toStringEntries());

    store.writeInode(MutableInodeDirectory.create(1, 0, "dir", CreateDirectoryContext.defaults()));
    assertEquals("dir", store.get(1).get().getName());
    assertThat(store.toStringEntries(), containsString("name=dir"));
  }