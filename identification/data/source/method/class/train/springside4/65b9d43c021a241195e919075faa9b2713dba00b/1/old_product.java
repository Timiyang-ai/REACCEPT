public static void write(final String data, final OutputStream output) throws IOException {
		if (data != null) {
			output.write(data.getBytes(Charsets.UTF_8));
		}
	}