@Transactional(readOnly = true)
	public Patient getPatientByUuid(String uuid) throws APIException;