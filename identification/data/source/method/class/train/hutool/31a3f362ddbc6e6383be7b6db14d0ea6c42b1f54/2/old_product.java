public static String get(String urlString) throws IOException {
		return HttpRequest.get(urlString).execute().body();
	}