public static File touch(File file) throws IOException {
		if(null == file){
			return null;
		}
		
		mkParentDirs(file);
		if(false == file.exists()) {
			file.createNewFile();
		}
		return file;
	}