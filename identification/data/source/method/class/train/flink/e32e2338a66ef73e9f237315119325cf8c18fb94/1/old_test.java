	private void uploadPart(final byte[] content) throws IOException {
		RefCountedBufferingFileStream partFile = writeContent(content);

		// as in the production code, we assume that a file containing
		// a completed part is closed before being passed to the uploader.

		partFile.close();

		multiPartUploadUnderTest.uploadPart(partFile);
	}