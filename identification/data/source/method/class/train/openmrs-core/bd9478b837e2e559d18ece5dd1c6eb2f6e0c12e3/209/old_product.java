public Role getRole(String r) throws APIException {
		return context.getDAOContext().getUserDAO().getRole(r);
	}