public static String get(String urlString, String customCharset, boolean isPassCodeError) throws IOException {
		HashMap<String, Object> headers = new HashMap<String, Object>();
		headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.83 Safari/537.1");
		
		return request("GET", urlString, customCharset, isPassCodeError, headers);
	}