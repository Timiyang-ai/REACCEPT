public String put(final String fileName, final MultipartFile multipartFile) throws IOException, RestErrorException {
		final String dirName = UUID.randomUUID().toString();
		final File srcDir = new File(restUploadDir, dirName);
		srcDir.mkdirs();
		String name = fileName == null ? multipartFile.getOriginalFilename() : fileName;
		final File storedFile = new File(srcDir, name);
		copyMultipartFileToLocalFile(multipartFile, storedFile);
		if (!storedFile.getName().endsWith(CloudifyConstants.PERMITTED_EXTENSION)) {
			throw new RestErrorException("Uploaded file's extension must be " 
					+ CloudifyConstants.PERMITTED_EXTENSION, storedFile.getAbsolutePath());
		}
		return dirName;
	}