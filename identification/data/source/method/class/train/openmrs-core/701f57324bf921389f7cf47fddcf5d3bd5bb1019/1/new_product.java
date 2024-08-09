public static void copyFile(InputStream inputStream, OutputStream outputStream) throws IOException {

		if (inputStream == null || outputStream == null) {
			if (outputStream != null) {
				IOUtils.closeQuietly(outputStream);
			}
			return;
		}

		try {
			IOUtils.copy(inputStream, outputStream);
		} finally {
			IOUtils.closeQuietly(outputStream);
		}

	}