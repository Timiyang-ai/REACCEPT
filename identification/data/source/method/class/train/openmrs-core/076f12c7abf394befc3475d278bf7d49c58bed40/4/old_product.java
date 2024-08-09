@Transactional(readOnly = true)
	@Authorized({ PrivilegeConstants.VIEW_PATIENTS })
	public List<Patient> getPatients(String name, String identifier, List<PatientIdentifierType> identifierTypes,
	                                 boolean matchIdentifierExactly, int start, Integer length) throws APIException;