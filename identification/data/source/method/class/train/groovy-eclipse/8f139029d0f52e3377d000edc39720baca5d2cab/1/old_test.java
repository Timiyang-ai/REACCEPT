	private void analyzeManifestContents(String contents) throws IOException {
		InputStream stream = new ByteArrayInputStream(contents.getBytes());
		try {
			this.manifestAnalyzer.analyzeManifestContents(stream);
		} finally {
			stream.close();
		}
	}