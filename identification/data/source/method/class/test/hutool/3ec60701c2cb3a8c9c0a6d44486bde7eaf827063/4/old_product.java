public static boolean del(File file) throws IORuntimeException {
		if (file == null || file.exists() == false) {
			return true;
		}

		if (file.isDirectory()) {
			clean(file);
		}
		return file.delete();
	}