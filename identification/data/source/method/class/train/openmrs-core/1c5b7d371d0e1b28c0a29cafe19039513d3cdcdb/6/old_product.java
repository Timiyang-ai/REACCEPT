public void logout() {
		user = null;
		getDaoContext().logout();
	}