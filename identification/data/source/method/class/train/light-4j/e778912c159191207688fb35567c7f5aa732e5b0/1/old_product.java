public static SSLContext createSSLContext() throws IOException {
    	Map<String, Object> tlsMap = (Map<String, Object>)config.get(TLS);
    	
    	return null==tlsMap?null:createSSLContext((String)tlsMap.get(TLSConfig.DEFAULT_GROUP_KEY));
    }