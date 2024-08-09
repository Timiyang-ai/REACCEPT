public static File touch(String fullFilePath) throws IOException {
		if(fullFilePath == null) {
			return null;
		}
		File file = new File(fullFilePath);
		
		file.getParentFile().mkdirs();
		if(!file.exists()) file.createNewFile();
		return file;
	}