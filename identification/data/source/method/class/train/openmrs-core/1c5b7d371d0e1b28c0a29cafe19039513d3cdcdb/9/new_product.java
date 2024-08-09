public void logout() {
		user = null;
		getDAOContext().logout();
	}