public boolean analyzeManifestContents(InputStream inputStream) throws IOException {
		char[] chars = Util.getInputStreamAsCharArray(inputStream, -1, Util.UTF_8);
		return analyzeManifestContents(chars);
	}