@Deprecated
	public void changePassword(User u, String pw) throws APIException {
		dao.changePassword(u, pw);
	}