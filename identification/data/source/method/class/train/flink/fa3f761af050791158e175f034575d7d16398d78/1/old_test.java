@Test
	public void temporary() throws Exception {
		File folderRoot = temporaryFolder.getRoot();
		File folder = new File(folderRoot, String.valueOf(UUID.randomUUID()));
		Assert.assertTrue(folder.mkdirs());
		Path folderPath = new Path(folder.toURI());
		SnapshotDirectory tmpSnapshotDirectory = SnapshotDirectory.temporary(folderPath);
		// temporary snapshot directories should not return a handle, because they will be deleted.
		Assert.assertNull(tmpSnapshotDirectory.completeSnapshotAndGetHandle());
		// check that the directory is deleted even after we called #completeSnapshotAndGetHandle.
		Assert.assertTrue(tmpSnapshotDirectory.cleanup());
		Assert.assertFalse(folder.exists());
	}