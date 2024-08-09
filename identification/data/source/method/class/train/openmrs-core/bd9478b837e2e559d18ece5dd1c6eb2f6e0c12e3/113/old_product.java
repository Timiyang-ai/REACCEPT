@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.GET_ENCOUNTERS })
	public List<Encounter> getEncounters(String query, Integer patientId, Integer start, Integer length,
	        boolean includeVoided) throws APIException;