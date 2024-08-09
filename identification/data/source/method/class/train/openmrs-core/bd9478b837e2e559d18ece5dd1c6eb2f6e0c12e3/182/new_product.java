public Privilege getPrivilege(String p) throws APIException {
		return getUserDAO().getPrivilege(p);
	}