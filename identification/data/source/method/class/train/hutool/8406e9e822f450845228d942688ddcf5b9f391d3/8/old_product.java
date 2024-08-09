public static File copy(String srcPath, String destPath, boolean isOverride) throws IOException {
		return copy(file(srcPath), file(destPath), isOverride);
	}