@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_PERSONS })
	public PersonAttribute getPersonAttributeByUuid(String uuid) throws APIException;