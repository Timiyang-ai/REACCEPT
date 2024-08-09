@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_PERSONS })
	public Person getPerson(Integer personId) throws APIException;