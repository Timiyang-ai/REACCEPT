public static File touch(String fullFilePath) throws IOException {
		if(fullFilePath == null) {
			return null;
		}
		return touch(file(fullFilePath));
	}