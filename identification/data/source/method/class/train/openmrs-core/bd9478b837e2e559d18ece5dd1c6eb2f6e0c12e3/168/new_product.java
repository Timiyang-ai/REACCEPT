public void changePassword(String pw, String pw2) throws APIException {
		getUserDAO().changePassword(pw, pw2);
	}