public static String get(String urlString, String customCharset, boolean isPassCodeError) throws IOException {
		final URL url = new URL(urlString);
		final String host = url.getHost();
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.83 Safari/537.1");
		final String cookie = cookies.get(host);
		if (cookie != null) conn.addRequestProperty("Cookie", cookie);

		conn.setRequestMethod("GET");
		conn.setDoInput(true);

		if (conn.getResponseCode() != 200) {
			if (!isPassCodeError) {
				throw new IOException("Status code not 200!");
			}
		}

		final String setCookie = conn.getHeaderField("Set-Cookie");
		if (StrUtil.isBlank(setCookie) == false) {
			Log.debug("Set cookie: [{}]", setCookie);
			cookies.put(host, setCookie);
		}

		/* 获取内容 */
		String charset = getCharsetFromConn(conn);
		boolean isGetCharsetFromContent = false;
		if (StrUtil.isBlank(charset)) {
			charset = customCharset;
			isGetCharsetFromContent = true;
		}
		String content = getString(conn.getInputStream(), charset, isGetCharsetFromContent);
		conn.disconnect();

		return content;
	}