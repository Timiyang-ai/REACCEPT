public static File touch(String parent, String path) throws IOException {
		return touch(file(parent, path));
	}