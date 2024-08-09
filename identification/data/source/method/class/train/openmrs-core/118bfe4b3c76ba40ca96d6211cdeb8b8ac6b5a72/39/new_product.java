@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_PERSONS })
	public PersonName getPersonNameByUuid(String uuid) throws APIException;