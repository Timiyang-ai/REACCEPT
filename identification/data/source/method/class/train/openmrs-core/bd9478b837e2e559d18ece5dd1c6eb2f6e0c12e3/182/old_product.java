public Privilege getPrivilege(String p) throws APIException {
		return context.getDAOContext().getUserDAO().getPrivilege(p);
	}