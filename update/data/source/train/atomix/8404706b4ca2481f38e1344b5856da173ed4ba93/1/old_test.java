@Test
  public void testCreateSnapshotFileName() throws Exception {
    assertEquals(SnapshotFile.createSnapshotFileName("foo", 1, 2), "foo-1-2.snapshot");
    assertEquals(SnapshotFile.createSnapshotFileName("foo-bar", 1, 2), "foo-bar-1-2.snapshot");
  }