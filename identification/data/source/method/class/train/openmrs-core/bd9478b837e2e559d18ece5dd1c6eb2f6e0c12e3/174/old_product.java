public User getUserByUsername(String username) throws APIException {
		return context.getDAOContext().getUserDAO().getUserByUsername(username);
	}