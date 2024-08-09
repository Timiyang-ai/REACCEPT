public synchronized void logout() throws XmlRpcFault {
		try {
			invoke("LogOut", token);
		} catch (XmlRpcFault e) {
			// anonymous users will always get an 401 Unauthorized when trying to logout,
			// so we ignore the status of the logout response
		} finally {
			token = null;
		}
	}