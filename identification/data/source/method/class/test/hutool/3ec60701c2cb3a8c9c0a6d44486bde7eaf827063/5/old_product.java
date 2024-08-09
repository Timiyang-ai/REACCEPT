public static File touch(File parent, String path) throws IOException {
		return touch(file(parent, path));
	}