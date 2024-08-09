@Test
  public void testCreateSnapshotFileName() throws Exception {
    assertEquals(SnapshotFile.createSnapshotFileName("test", 1), "test-1.snapshot");
    assertEquals(SnapshotFile.createSnapshotFileName("test", 2), "test-2.snapshot");
  }