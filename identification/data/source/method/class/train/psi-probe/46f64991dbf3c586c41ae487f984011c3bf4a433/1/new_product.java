@Override
	public File getFile() {
		File[] files = (File[]) Instruments.getField(getTarget(), "files");
		if (files == null || files.length == 0) {
			throw new IllegalStateException("File handler does not manage any files");
		}
		return files[LATEST_FILE_INDEX];
	}