@Test
	public void remove() throws IOException
	{
		java.io.File file = java.io.File.createTempFile("wicket-test--", ".tmp");
		assertTrue("The just created file should exist!", file.exists());

		boolean removed = Files.remove(file);
		assertFalse("The just removed file should not exist!", file.exists());
		assertTrue("Files.remove(file) should remove the file", removed);

		// try to remove non-existing file
		removed = Files.remove(file);
		assertFalse("The just removed file should not exist!", file.exists());
		assertFalse("Files.remove(file) should not remove the file", removed);
	}