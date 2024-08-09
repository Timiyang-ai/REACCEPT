public static URL file2url(final File file) throws MalformedURLException {
		if (file == null) {
			return null;
		}
		try {
			return file.getCanonicalFile().toURI().toURL();
		}
		catch (MalformedURLException mue) {
			throw mue;
		}
		catch (IOException | NoSuchMethodError ioe) {
			throw new MalformedURLException("Cannot convert: " + file.getName() + " to url");
		}
	}