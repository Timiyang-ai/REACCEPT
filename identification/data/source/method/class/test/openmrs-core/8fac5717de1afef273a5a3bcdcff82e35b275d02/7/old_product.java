@Deprecated
    @Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_PERSONS })
	public Person getPerson(User user) throws APIException;