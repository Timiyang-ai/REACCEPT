public List<User> getUsers() throws APIException {
		return context.getDAOContext().getUserDAO().getUsers();
	}