@Override
	public File getFile() {
		String pattern = (String) Instruments.getField(getTarget(), "pattern");
		String logFilePath = pattern.replace("%g", String.valueOf(LATEST_FILE_INDEX));
		return new File(logFilePath);
	}