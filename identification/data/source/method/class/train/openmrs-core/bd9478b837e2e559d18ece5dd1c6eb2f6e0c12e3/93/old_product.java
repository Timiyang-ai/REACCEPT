public void changePassword(String pw, String pw2) throws APIException {
		context.getDAOContext().getUserDAO().changePassword(pw, pw2);
	}