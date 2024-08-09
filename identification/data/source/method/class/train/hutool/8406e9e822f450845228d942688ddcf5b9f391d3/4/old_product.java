public static void copy(File src, File dest, boolean isOverride) throws IOException {
		//check
		if (! src.exists()) {
			throw new FileNotFoundException("File not exist: " + src);
		}
		if (! src.isFile()) {
			throw new IOException("Not a file:" + src);
		}
		if (equals(src, dest)) {
			throw new IOException("Files '" + src + "' and '" + dest + "' are equal");
		}
		
		if (dest.exists()) {
			if (dest.isDirectory()) {
				dest = new File(dest, src.getName());
			}
			if (dest.exists() && ! isOverride) {
				throw new IOException("File already exist: " + dest);
			}
		}

		// do copy file
		FileInputStream input = new FileInputStream(src);
		FileOutputStream output = new FileOutputStream(dest);
		try {
			IoUtil.copy(input, output);
		} finally {
			close(output);
			close(input);
		}

		if (src.length() != dest.length()) {
			throw new IOException("Copy file failed of '" + src + "' to '" + dest + "' due to different sizes");
		}
	}