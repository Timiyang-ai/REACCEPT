@Test
	public void remove() throws IOException
	{
		assertFalse("'null' files are not deleted.", Files.remove(null));

		assertFalse("Non existing files are not deleted.", Files.remove(new File(
			"/somethingThatDoesntExistsOnMostMachines-111111111111111111111111111111")));

		java.io.File file = java.io.File.createTempFile("wicket-test--", ".tmp");
		assertTrue("The just created file should exist!", file.exists());

		boolean removed = Files.remove(file);
		assertFalse("The just removed file should not exist!", file.exists());
		assertTrue("Files.remove(file) should remove the file", removed);

		// try to remove non-existing file
		removed = Files.remove(file);
		assertFalse("The just removed file should not exist!", file.exists());
		assertFalse("Files.remove(file) should not remove the file", removed);

		// try to remove a folder
		java.io.File folder = new File(System.getProperty("java.io.tmpdir"), "wicket-test-folder");
		Files.mkdirs(folder);
		assertTrue(folder.isDirectory());
		assertFalse("Should not be able to delete a folder, even empty one.", Files.remove(folder));
		assertTrue("Should not be able to delete a folder.", Files.removeFolder(folder));
	}