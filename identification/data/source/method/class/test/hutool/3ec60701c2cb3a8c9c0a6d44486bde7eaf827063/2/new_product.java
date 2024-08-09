public static File touch(String fullFilePath) throws IORuntimeException {
		if (fullFilePath == null) {
			return null;
		}
		return touch(file(fullFilePath));
	}