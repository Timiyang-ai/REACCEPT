public synchronized void login(String username, String password, String language) throws XmlRpcFault {
		Map<?, ?> response = invoke("LogIn", username, password, language, useragent);
		
		// set session token
		token = response.get("token").toString();
	}