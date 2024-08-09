public static String get(String urlString, String customCharset) {
		return HttpRequest.get(urlString).charset(customCharset).execute().body();
	}