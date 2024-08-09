@Authorized( { PrivilegeConstants.VIEW_PATIENT_PROGRAMS })
	@Transactional(readOnly = true)
	public List<Concept> getPossibleOutcomes(Integer programId);