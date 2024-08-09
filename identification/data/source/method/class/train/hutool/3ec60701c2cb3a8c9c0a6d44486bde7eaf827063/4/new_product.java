public static File touch(File file) throws IOException {
		if (null == file) {
			return null;
		}

		if (false == file.exists()) {
			mkParentDirs(file);
			file.createNewFile();
		}
		return file;
	}