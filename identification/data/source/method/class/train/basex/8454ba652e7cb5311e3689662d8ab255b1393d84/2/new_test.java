@Test
  public void testCreate() {
    assertEquals("mkdir 0", 3, dbfs.mkdir("/a", 0040755));
    // create returns id
    assertEquals("create", 5, dbfs.create("/a/file.txt", 0100644));
    // wrong mode
    assertEquals("create", -1, dbfs.create("/a/dir", 0040755));
    // no parent directory to insert
    assertEquals("create", -1, dbfs.create("/a/b/c/file.txt", 0100644));
    assertEquals("mkdir 0", 7, dbfs.mkdir("/a/b", 0040755));
    query("/");
    assertEquals("create", 9, dbfs.create("/a/b/file.txt", 0100644));
  }