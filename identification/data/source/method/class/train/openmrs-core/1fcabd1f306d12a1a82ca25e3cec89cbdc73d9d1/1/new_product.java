public static File url2file(final URL url) {
		if (url == null || !"file".equalsIgnoreCase(url.getProtocol())) {
			return null;
		}	
		return new File(url.getFile().replaceAll("%20", " "));
	}