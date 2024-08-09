@SuppressWarnings("unchecked")
	public synchronized void login(String username, String password, String language) throws XmlRpcFault {
		Map<String, String> response = (Map<String, String>) invoke("LogIn", username, password, language, useragent);
		checkStatus(response.get("status"));
		
		token = response.get("token");
	}