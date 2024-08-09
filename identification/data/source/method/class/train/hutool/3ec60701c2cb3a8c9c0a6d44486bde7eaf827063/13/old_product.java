public static File touch(File file) throws IOException {
		if(null == file){
			return null;
		}
		
		file.getParentFile().mkdirs();
		if(false == file.exists()) {
			file.createNewFile();
		}
		return file;
	}