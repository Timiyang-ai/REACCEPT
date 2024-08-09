public static String get(String urlString) {
		return HttpRequest.get(urlString).execute().body();
	}