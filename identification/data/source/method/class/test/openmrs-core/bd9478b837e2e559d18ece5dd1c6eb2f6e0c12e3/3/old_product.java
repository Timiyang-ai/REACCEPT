public User getUser(Integer userId) throws APIException {
		return context.getDAOContext().getUserDAO().getUser(userId);
	}