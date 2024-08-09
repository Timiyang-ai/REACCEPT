public synchronized void logout() throws XmlRpcFault {
		try {
			invoke("LogOut", token);
			
			// anonymous users will always get a 401 Unauthorized when trying to logout
			// do not check status for logout response
			// checkStatus(response.get("status"));
		} finally {
			token = null;
		}
	}