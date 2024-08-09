public static String get(String urlString, String customCharset) throws IOException {
		return HttpRequest.get(urlString).charset(customCharset).execute().body();
	}