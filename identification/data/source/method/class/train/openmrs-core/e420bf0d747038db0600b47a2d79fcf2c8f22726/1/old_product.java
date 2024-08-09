@Deprecated
	public void changePassword(User u, String pw) throws APIException {
		OpenmrsUtil.validatePassword(u.getUsername(), pw, u.getSystemId());
		dao.changePassword(u, pw);
	}