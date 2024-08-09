public void createUser(User user, String password) throws APIException {
		context.getDAOContext().getUserDAO().createUser(user, password);
	}