@Authorized( { OpenmrsConstants.PRIV_VIEW_PROGRAMS })
	@Transactional(readOnly = true)
	public Program getProgramByName(String name) throws APIException;