  @Test
  public void getattr() throws Exception {
    // set up status
    FileInfo info = new FileInfo();
    info.setLength(4 * Constants.KB + 1);
    info.setLastModificationTimeMs(1000);
    String userName = System.getProperty("user.name");
    info.setOwner(userName);
    info.setGroup(AlluxioFuseUtils.getGroupName(userName));
    info.setFolder(true);
    info.setMode(123);
    info.setCompleted(true);
    URIStatus status = new URIStatus(info);

    // mock fs
    when(mFileSystem.getStatus(any(AlluxioURI.class))).thenReturn(status);

    FileStat stat = new FileStat(Runtime.getSystemRuntime());
    assertEquals(0, mFuseFs.getattr("/foo", stat));
    assertEquals(status.getLength(), stat.st_size.longValue());
    assertEquals(9, stat.st_blocks.intValue());
    assertEquals(status.getLastModificationTimeMs() / 1000, stat.st_ctim.tv_sec.get());
    assertEquals((status.getLastModificationTimeMs() % 1000) * 1000,
        stat.st_ctim.tv_nsec.longValue());
    assertEquals(status.getLastModificationTimeMs() / 1000, stat.st_mtim.tv_sec.get());
    assertEquals((status.getLastModificationTimeMs() % 1000) * 1000,
        stat.st_mtim.tv_nsec.longValue());
    assertEquals(AlluxioFuseUtils.getUid(System.getProperty("user.name")), stat.st_uid.get());
    assertEquals(AlluxioFuseUtils.getGid(System.getProperty("user.name")), stat.st_gid.get());
    assertEquals(123 | FileStat.S_IFDIR, stat.st_mode.intValue());
  }