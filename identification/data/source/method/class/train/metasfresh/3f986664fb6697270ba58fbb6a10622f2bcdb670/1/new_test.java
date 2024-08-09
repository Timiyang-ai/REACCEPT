	@Test
	public void test_getMimeType()
	{
		test_getMimeType(MimeType.TYPE_PDF, "report.pdf");
		test_getMimeType(MimeType.TYPE_TextPlain, "report.txt");
		test_getMimeType(MimeType.TYPE_BINARY, "report.unknown-extension");
	}