public List<User> getUsersByRole(Role role) throws APIException {
		return context.getDAOContext().getUserDAO().getUsersByRole(role);
	}