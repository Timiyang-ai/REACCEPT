public HttpConnection getConnection() {
    	if(customSslContext != null) {
    	    return new DefaultHttpConnection(customSslContext);
    	} else {
    	    return new DefaultHttpConnection();
    	}
	}