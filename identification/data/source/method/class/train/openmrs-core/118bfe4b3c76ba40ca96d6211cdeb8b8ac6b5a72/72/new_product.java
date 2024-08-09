@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_PERSONS })
	public PersonAddress getPersonAddressByUuid(String uuid) throws APIException;