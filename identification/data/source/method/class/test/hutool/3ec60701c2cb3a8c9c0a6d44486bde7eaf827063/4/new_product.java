public static boolean del(File file) throws IORuntimeException {
		if (file == null || false == file.exists()) {
			return false;
		}

		if (file.isDirectory()) {
			clean(file);
		}
		try {
			Files.delete(file.toPath());
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
		return true;
	}