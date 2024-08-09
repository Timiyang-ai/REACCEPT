public String put(final String fileName, final MultipartFile multipartFile) throws IOException {
		final String dirName = UUID.randomUUID().toString();
		final File srcDir = new File(restUploadDir, dirName);
		srcDir.mkdirs();
		String name = fileName == null ? multipartFile.getOriginalFilename() : fileName;
		final File storedFile = new File(srcDir, name);
		copyMultipartFileToLocalFile(multipartFile, storedFile);
		return dirName;
	}