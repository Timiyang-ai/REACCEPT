	@Test
	public void copyFile_shouldNotCopyTheOutputstreamWhenOutputstreamIsNull() throws IOException {
		String exampleInputStreamString = "ExampleInputStream";
		ByteArrayInputStream input = new ByteArrayInputStream(exampleInputStreamString.getBytes());

		OutputStream output = null;

		OpenmrsUtil.copyFile(input, output);

		assertNull(output);
		assertNotNull(input);
	}