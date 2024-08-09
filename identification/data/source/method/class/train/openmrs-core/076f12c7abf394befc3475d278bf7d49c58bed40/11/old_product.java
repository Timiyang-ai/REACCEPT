@Transactional(readOnly = true)
	@Authorized({ PrivilegeConstants.VIEW_PATIENTS })
	public List<Patient> getPatients(String query, int start, Integer length) throws APIException;